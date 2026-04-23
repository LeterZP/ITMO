CREATE TYPE ftimes AS (creation_time DATE, disposal_time DATE);

CREATE FUNCTION check_frames()
RETURNS TRIGGER AS $$
DECLARE
	all_times ftimes[];
	element ftimes;
	flag BOOL := false;
BEGIN
	SELECT ARRAY(
		SELECT DISTINCT row(frame.creation_time, frame.disposal_time)::ftimes FROM frame
        	JOIN galaxy ON frame.galaxy_name = galaxy.galaxy_name
        	JOIN nebula ON nebula.galaxy_name = galaxy.galaxy_name
        	JOIN star ON star.nebula_name = nebula.nebula_name
        	JOIN human ON human.passport_id = frame.human
		WHERE NEW.star_name = star.star_name AND NEW.human = human.passport_id
	) INTO all_times;

	FOREACH element IN ARRAY all_times LOOP
        	IF (NEW.collision_time BETWEEN element.creation_time AND element.disposal_time) THEN
			flag = true;
		END IF;
    	END LOOP;

	IF NOT flag AND (TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
		RAISE EXCEPTION 'Запись не может быть создана или изменена, так как данное взаимодействие не было замечено в установленной раме';
	END IF;

	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER register_collision_time
BEFORE INSERT OR UPDATE ON star_collision
FOR EACH ROW
EXECUTE FUNCTION check_frames();


