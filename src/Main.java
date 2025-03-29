import java.util.*;

public class Main {
    public static void main(String[] args) {

    }

    public void firstProblem() {
        Scanner nScanner = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter num of problems");

        int number = nScanner.nextInt();

        for(int i = 0; i < number; i++) {
            System.out.println("enter data:");
            String str = scanner.nextLine();
            String[] data = str.split(" ");

            if(data.length == 3) {
                String letter = data[0];

                int first = Integer.parseInt(data[1].split(" ")[0]);
                int second = Integer.parseInt(data[2]);

                if(letter.equals("M")) {
                    int output = first + second;
                    System.out.println(output);
                } else if(letter.equals("E")) {
                    String output = Integer.toString(first) + Integer.toString(second);
                    System.out.println(output);
                }
            }
        }
    }

    public void secondProblem() {
        Scanner fScanner = new Scanner(System.in);
        Scanner sScanner = new Scanner(System.in);

        System.out.println("enter first line");

        String[] nums = fScanner.nextLine().split(" ");

        int numOfMacs = Integer.parseInt(nums[0]);
        int wRestriction = Integer.parseInt(nums[1]);

        System.out.println("enter second line");

        String[] macs = sScanner.nextLine().split(" ");
        List<Integer> macWeights = new ArrayList<Integer>();

        List<Integer> ints = new ArrayList<Integer>();

        for(int i = 0; i < numOfMacs; i++) {
            macWeights.add(Integer.parseInt(macs[i]));
        }

        int cur = biggestNum(macWeights, Integer.MAX_VALUE) + 1;

        while(cur > 0) {
            int bNum = biggestNum(macWeights, cur);

            ints.add(bNum);

            cur = bNum;
            cur -= wRestriction;
        }

        int finalNum = 0;

        for(int i = 0; i < ints.size(); i++) {
            finalNum += ints.get(i);
            System.out.println(ints.get(i));
        }

        System.out.println(finalNum);
    }

    // second problem uses:
    public static int biggestNum(List<Integer> array, int bound) { // this is a recursive function.
        int biggestNum = -1;

        for(int i = 0; i < array.size(); i++) {
            if(array.get(i) != -1) {
                if(array.get(i) >= biggestNum && array.get(i) <= bound) {
                    biggestNum = array.get(i);
                }
            }
        }

        return biggestNum;
    }

    public void thirdProblem() {
        Scanner fScanner = new Scanner(System.in);
        Scanner sScanner = new Scanner(System.in);

        System.out.println("enter num of questions");

        String[] firstLine = fScanner.nextLine().split(" ");

        int contestLength = Integer.parseInt(firstLine[0]);
        int numQuestions = Integer.parseInt(firstLine[1]);

        List<Question> questions = new ArrayList<>();

        for(int i = 0; i < numQuestions; i++) {
            String[] secondLine = sScanner.nextLine().split(" ");

            String question = secondLine[0];

            int start = Integer.parseInt(secondLine[1]);
            int end = Integer.parseInt(secondLine[2]);
            int points = Integer.parseInt(secondLine[3]);

            Question q = new Question(question, start, end, points);
            questions.add(q);
        }

        int maxPoints = 0;

        for(Question q1 : questions) {
            for(Question q2 : questions) {
                if(!q1.equals(q2)) {
                    int timeTakes = (q1.end - q1.start) + (q2.end - q2.start);

                    if(timeTakes > contestLength) continue;
                    if(q1.start == q2.start || q1.end == q2.end || q1.start == q2.end || q2.start == q1.end) continue;

                    if((q1.points + q2.points) > maxPoints)
                        maxPoints = (q1.points + q2.points);
                }
            }

            int timeTakes = (q1.end - q1.start);

            if(timeTakes > contestLength)
                continue;

            if(q1.points > maxPoints)
                maxPoints = (q1.points);
        }

        System.out.println(maxPoints);
    }

    public void fifthProblem() {
        Scanner fScanner = new Scanner(System.in);

        String[][] room;
        String[] firstLine = fScanner.nextLine().split(" ");

        int numLasers = Integer.parseInt(firstLine[0]);
        int width = Integer.parseInt(firstLine[1]);
        int height = Integer.parseInt(firstLine[2]);

        room = new String[height][width]; // using 2 dimensional array. u dont know this yet erik! XP

        // populate array
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                room[y][x] = ".";
            }
        }

        List<Laser> lasers = new ArrayList<>();

        for(int i = 0; i < numLasers; i++) {
            String[] secondLine = fScanner.nextLine().split(" ");

            int x1 = Integer.parseInt(secondLine[0]);
            int y1 = Integer.parseInt(secondLine[1]);
            int x2 = Integer.parseInt(secondLine[2]);
            int y2 = Integer.parseInt(secondLine[3]);

            Laser laser = new Laser(x1, y1, x2, y2);
            lasers.add(laser);
        }

        for(Laser laser : lasers) {
            int xDif = (laser.x2 - laser.x1);
            int yDif = (laser.y2 - laser.y1);

            int xStart = laser.x1;
            int yStart = laser.y1;

            int xInc = 0;
            int yInc = 0;

            if(xDif > 0) xInc = 1;
            else if(xDif < 0) xInc = -1;

            if(yDif > 0) yInc = 1;
            else if(yDif < 0) yInc = -1;

            int curX = xStart;
            int curY = yStart;

            int endX = xStart + xDif;
            int endY = yStart + yDif;

            System.out.println(curX + "," + curY + " <-> " + endX + "," + endY);
            System.out.println("start loop!");

            while(true) {
                System.out.println(curX + "," + curY);
                room[curY][curX] = "X";

                if(curX == endX && curY == endY) {
                    break; // exit while loop, not RETURN whole main method.
                }

                curX += xInc;
                curY += yInc;
            }
        }

        int freeSpaces = 0;

        for(int x = 0; x < room.length; x++) {
            for(int y = 0; y < room[x].length; y++) {
                System.out.print(room[x][y]);
                if(room[x][y] == ".") {
                    freeSpaces++;
                }
            }

            System.out.println();
        }

        System.out.println(freeSpaces);
    }
}

class Question { // used for question 3
    public String question;
    public int start, end, points;

    public Question(String question, int start, int end, int points) {
        this.question = question;
        this.start = start;
        this.end = end;
        this.points = points;
    }
}

class Laser {
    public int x1, y1, x2, y2;

    public Laser(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}