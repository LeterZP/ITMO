import polars


table = polars.read_ods("ИнфЛаб5.ods", has_header=False, columns=range(25),
                        drop_empty_rows=False, drop_empty_cols=False)
table = table.with_columns(polars.col("column_3").cast(polars.String))
for i in range(1, 26):
    if i == 6: table = table.drop("column_"+str(i))
    else: table = table.fill_null(" ")
polars.Config.set_tbl_rows(100)
polars.Config.set_tbl_cols(100)
# print(table.limit(15))
table = table.with_columns(polars.concat_str("column_4", "column_5"))
table = table.drop("column_5")
for i in range(7, 25):
    table = table.with_columns(polars.concat_str("column_7", "column_"+str(i+1)))
    table = table.drop("column_"+str(i+1))
print(table.limit(15))
