package com.example.shaunakbasu.capstone.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by shaunak basu on 14-11-2016.
 */
public class CalorieIntakeColumns {

    @DataType(DataType.Type.INTEGER) @PrimaryKey
    @AutoIncrement
    public static final String _ID = "_id";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String DATE = "date";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String AMOUNT = "amount";

}
