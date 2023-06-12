import java.util.Scanner;
import java.util.Arrays;

class Team implements Comparable<Team> {
    String name;
    int wins, draws, losses, goalsScored, goalsLoss, goalAverage;
    int points;

    Team(String name, int w, int d, int l, int g, int gL) {
        this.name = name;
        wins = w;
        draws = d;
        losses = l;
        goalsScored = g;
        goalsLoss = gL;

        setPoints();
        goalAverage = goalsScored - goalsLoss;
    }

    public void setPoints() {
        points = 3*wins + draws;
    }
    public int compareTo(Team t) {
        if (points == t.points) {
            if (goalAverage == t.goalAverage) {
                return name.compareTo(t.name);
            }
            return t.goalAverage - goalAverage;
        }
        return t.points - points;
    }

    public String toString() {
        return name + " " + points + " " + goalAverage;
    }
}


public class ED163 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Team[] teams = new Team[N];
        for (int i=0; i<N; i++) {
            String name = in.next();
            int wins = in.nextInt();
            int draws = in.nextInt();
            int losses = in.nextInt();
            int goalsScored = in.nextInt();
            int goalsLoss = in.nextInt();
            teams[i] = new Team(name, wins, draws, losses, goalsScored, goalsLoss);
        }
        
        Arrays.sort(teams);
        for (int i=0; i<N; i++) 
            System.out.println(teams[i]);
    }
}