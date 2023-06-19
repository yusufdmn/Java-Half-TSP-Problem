public class Helper {

    public static int calculateDistance(City city1, City city2) {
        double dx = city1.x - city2.x;
        double dy = city1.y - city2.y;
        return (int) Math.round(Math.sqrt(dx * dx + dy * dy)) ;
    }


    public static String fromArrayToString(int[] array, int tourLength){

        StringBuffer output = new StringBuffer( "" + tourLength);

        for(int each : array){
            output.append("\n" + each);
            //output+= "\n" + each;
        }

        output.append("\n");
        String outputStr = output.toString();
        return outputStr;
    }

}







/*import java.util.List;

public class Helper {

    public static int[][] calculateDistanceMatrix(List<City> cities) {
        int numCities = cities.size();
        int[][] distanceMatrix = new int[numCities][numCities];

        for (int i = 0; i < numCities; i++) {
            City city1 = cities.get(i);
            for (int j = 0; j < numCities; j++) {
                if (i == j) {
                    distanceMatrix[i][j] = 0;
                } else {
                    City city2 = cities.get(j);
                    int distance = calculateDistance(city1, city2);
                    distanceMatrix[i][j] = distance;
                }
            }
        }

        return distanceMatrix;
    }

    public static int calculateDistance(City city1, City city2) {
        double dx = city1.x - city2.x;
        double dy = city1.y - city2.y;
        return (int) Math.round(Math.sqrt(dx * dx + dy * dy)) ;
    }


    public static String fromArrayToString(int[] array, int tourLength){

        String output = "" + tourLength;

        for(int each : array){
            output+= "\n" + each;
        }
        output += "\n";
        return output;
    }

}
*/