package it.unipi.dii.lsmsd.pokeMongo.persistence;

public enum Filter {
    NAME, POKEDEX_ID, MIN_WEIGHT, MAX_WEIGHT, MIN_HEIGHT, MAX_HEIGHT,
    TYPE1, TYPE2, MIN_CATCTH_RATE, MAX_CATCH_RATE, MIN_POINTS, MAX_POINTS;

    public static Filter interfaceStringToFilter(String field){
        try {
            switch (field) {
                case "Name":
                    return NAME;
                case "Pokedex ID":
                    return POKEDEX_ID;
                case "Min Weight":
                    return MIN_WEIGHT;
                case "Max Weight":
                    return MAX_WEIGHT;
                case "Min Height":
                    return MIN_HEIGHT;
                case "Max Height":
                    return MAX_HEIGHT;
                case "Type1":
                    return TYPE1;
                case "Type2":
                    return TYPE2;
                case "Min Catch Rate":
                    return MIN_CATCTH_RATE;
                case "Max Catch Rate":
                    return MAX_CATCH_RATE;
                case "Min Points":
                    return MIN_POINTS;
                case "Max Points":
                    return MAX_POINTS;
                default:
                    throw new Exception("String not recognized");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String FilterToInterfaceString(Filter f){
        try {
            switch (f) {
                case NAME:
                    return "Name";
                case POKEDEX_ID:
                    return "Pokedex ID";
                case MIN_WEIGHT:
                    return "Min Weight";
                case MAX_WEIGHT:
                    return "Max Weight";
                case MIN_HEIGHT:
                    return "Min Height";
                case MAX_HEIGHT:
                    return "Max Height";
                case TYPE1:
                    return "Type1";
                case TYPE2:
                    return "Type2";
                case MIN_CATCTH_RATE:
                    return "Min Catch Rate";
                case MAX_CATCH_RATE:
                    return "Max Catch Rate";
                case MIN_POINTS:
                    return "Min Points";
                case MAX_POINTS:
                    return "Max Points";
                default:
                    throw new Exception("String not recognized");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
