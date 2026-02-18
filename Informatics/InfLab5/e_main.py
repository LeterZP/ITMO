import polars
from great_tables import style, loc

table = polars.read_ods("ИнфЛаб5.ods", has_header=False, columns=range(25),
                        drop_empty_rows=False, drop_empty_cols=False)
table = table.with_columns(polars.col("column_3").cast(polars.String))
for i in range(1, 26):
    if i == 6: table = table.drop("column_"+str(i))
    else: table = table.fill_null(" ")
polars.Config.set_tbl_rows(100)
polars.Config.set_tbl_cols(100)
table = table.limit(15)
table = table.style
for column in range(1, 5):
    table = table.tab_style(
        style=style.borders(sides="top", color="#010190", style="solid", weight="3px"),
        locations=loc.body(columns="column_"+str(column), rows=[3]))
    table = table.tab_style(
        style=style.borders(sides="left", color="#010190", style="solid", weight="3px"),
        locations=loc.body(columns="column_"+str(column), rows=list(range(3, 15))))
    table = table.tab_style(
        style=style.borders(sides="bottom", color="#010190", style="solid", weight="3px"),
        locations=loc.body(columns="column_"+str(column), rows=[14]))
for column in [5, 25]:
    table = table.tab_style(
        style=style.borders(sides="top", color="#010190", style="solid", weight="3px"),
        locations=loc.body(columns="column_" + str(column), rows=[3]))
    table = table.tab_style(
        style=style.borders(sides="right", color="#010190", style="solid", weight="3px"),
        locations=loc.body(columns="column_" + str(column), rows=list(range(3, 15))))
    table = table.tab_style(
        style=style.borders(sides="bottom", color="#010190", style="solid", weight="3px"),
        locations=loc.body(columns="column_" + str(column), rows=[14]))
for column in range(7, 25):
    table = table.tab_style(
        style=style.borders(sides="top", color="#010190", style="solid", weight="3px"),
        locations=loc.body(columns="column_" + str(column), rows=[3]))
    table = table.tab_style(
        style=style.borders(sides="bottom", color="#010190", style="solid", weight="3px"),
        locations=loc.body(columns="column_" + str(column), rows=[14]))
table.show()