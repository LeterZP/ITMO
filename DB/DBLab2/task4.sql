SELECT ГРУППА FROM 
(
	SELECT ГРУППА, COUNT(ГРУППА) FROM Н_УЧЕНИКИ
	JOIN Н_ПЛАНЫ ON Н_УЧЕНИКИ.ПЛАН_ИД = Н_ПЛАНЫ.ИД
	JOIN Н_ОТДЕЛЫ ON Н_ОТДЕЛЫ.ИД = Н_ПЛАНЫ.ОТД_ИД
	WHERE Н_ОТДЕЛЫ.КОРОТКОЕ_ИМЯ = 'КТиУ'
	AND EXTRACT(YEAR FROM НАЧАЛО) = 2011
	GROUP BY ГРУППА
)
WHERE count = 5;
