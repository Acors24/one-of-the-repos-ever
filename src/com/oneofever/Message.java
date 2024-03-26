package com.oneofever;

public final class Message {
    public final class Error {
        // 1. negative value error messages
        public static final String HEIGHT_NEGATIVE = "Height length cannot be negative.";
        public static final String SIDE_NEGATIVE = "Side length cannot be negative.";
        public static final String DIAGONAL_NEGATIVE = "Diagonal length cannot be negative.";
        public static final String AREA_NEGATIVE = "Area cannot be negative.";

        // 2. wrong number of arguments error messages
        public static final String WRONG_NUMBER_OF_SIDES = "Wrong number of sides.";
        public static final String WRONG_NUMBER_OF_HEIGHTS = "Wrong number of heights.";
        public static final String TOO_FEW_ARGUMENTS = "Not enough arguments.";
        public static final String TOO_MANY_ARGUMENTS = "Too many arguments.";

        // 3. invalid length of sides
        public static final String DIAGONAL_TOO_SHORT = "Diagonal is too short.";
        public static final String SHORTER_IS_LONGER =
                "Shorter side is longer than the longer side.";

        // 4.
        public static final String PARSER_FAILED = "Parser failed: ";
    }

    public final class Info {
        // 1. parameter type
        public static final String SIDE_S = "side_s";
        public static final String SIDE_L = "side_l";
        public static final String SIDE = "side";
        public static final String HEIGHT = "height";
        public static final String DIAGONAL = "diagonal";
        public static final String AREA = "area";

        // 2. shape
        public static final String SQUARE = "square";
        public static final String RECTANGLE = "rectangle";

        // 3. data type
        public static final String DOUBLE = "Double";
    }
}
