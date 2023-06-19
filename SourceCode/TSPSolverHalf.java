import java.util.List;

public class TSPSolverHalf {

    private List<City> cities;
    private int numCities;
    private int[] bestTour;
    private int bestTourLength;

    public TSPSolverHalf(List<City> cities) {
        this.cities = cities;
        this.numCities = cities.size();
        this.bestTour = new int[ (int) Math.ceil(numCities / 2.0)];
        this.bestTourLength = Integer.MAX_VALUE;
    }

    public int[] solve() {      // return the best tour

        int[] currentTour = GetHalfTour(cities, (int) Math.ceil(numCities / 2.0));   // Get the half of the cities
        int currentTourLength = calculateTourLength(currentTour);

        while (twoOptSwap(currentTour, currentTourLength)) {       // Apply 2-opt
            currentTourLength = calculateTourLength(currentTour);
        }

        return bestTour;
    }



    public static int[] GetHalfTour(List<City> cities, int selectedCities) {
        int numCities = cities.size();
        int[] tour = new int[selectedCities];
        boolean[] visited = new boolean[numCities];

        int startCity = 0;

        tour[0] = startCity;
        visited[startCity] = true;

        for (int i = 1; i < selectedCities; i++) {
            int nextCity = findClosestCity(cities, tour[i - 1], visited);
            tour[i] = nextCity;
            visited[nextCity] = true;
        }

        return tour;
    }

    public static int findClosestCity(List<City> cities, int currentCity, boolean[] visited) {
        int numCities = cities.size();
        int closestCity = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int city = 0; city < numCities; city++) {
            if (!visited[city]) {
                int distance = Helper.calculateDistance(cities.get(currentCity), cities.get(city));
                if (distance < minDistance) {
                    closestCity = city;
                    minDistance = distance;
                }
            }
        }

        return closestCity;
    }


    private int calculateTourLength(int[] tour) {
        int length = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            length += Helper.calculateDistance(cities.get(tour[i]), cities.get(tour[i + 1]));
        }
        length += Helper.calculateDistance(cities.get(tour[tour.length - 1]), cities.get(tour[0]));
        return length;
    }


    private boolean twoOptSwap(int[] tour, int tourLength) {        // Implement 2-opt Algorithm
        boolean improvement = false;

        for (int i = 0; i < tour.length - 1; i++) {
            for (int j = i + 1; j < tour.length; j++) {
                int newTourLength = tourLength
                        - Helper.calculateDistance(cities.get(tour[i]), cities.get(tour[i + 1]))
                        - Helper.calculateDistance(cities.get(tour[j]), cities.get(tour[(j + 1) % tour.length]))
                        + Helper.calculateDistance(cities.get(tour[i]), cities.get(tour[j]))
                        + Helper.calculateDistance(cities.get(tour[i + 1]), cities.get(tour[(j + 1) % tour.length]));

                if (newTourLength < tourLength) {        // If new tour length is better than current, swap the cities
                    reverseSubtour(tour, i + 1, j);
                    improvement = true;
                    tourLength = newTourLength;

                    if (tourLength < bestTourLength) {      // If the new tour is the best, save it
                        bestTourLength = tourLength;
                        System.arraycopy(tour, 0, bestTour, 0, tour.length);
                    }
                }
            }
        }

        return improvement;
    }

    private void reverseSubtour(int[] tour, int start, int end) {
        while (start < end) {
            int temp = tour[start];
            tour[start] = tour[end];
            tour[end] = temp;
            start++;
            end--;
        }
    }

    public int getBestTourLength() {
        return bestTourLength;
    }
}

