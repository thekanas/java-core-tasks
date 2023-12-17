public class Main {
    public static void main(String[] args) {
        Human bob = new Human("Bob");
        Cat cat = new Cat("Barsik");
        Robot robot = new Robot("Xiomi");

        Participant[] participants = new Participant[]{bob, cat, robot};

        Obstacle[] obstacles = new Obstacle[]{new Wall(3), new Treadmill(200), new Wall(3)};

        for (Participant participant : participants) {
            for (Obstacle obstacle : obstacles) {
                boolean resultat = obstacle.obstace(participant);
                if (!resultat) break;
            }
        }
    }
}

interface Participant {
    boolean run(int distance);

    boolean jump(int height);
}

abstract class Obstacle {

    public abstract boolean obstace(Participant participant);

}

class Wall extends Obstacle {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean obstace(Participant participant) {
        if (participant.jump(height)) {
            //System.out.println(participant.toString() + " перепрыгнул стену высотой " + height + "м.");
            return true;
        } else {
            //System.out.println(participant.toString() + " не смог перепрыгнуть стену высотой " + height + "м.");
            return false;
        }

    }
}

class Treadmill extends Obstacle {
    private int distance;

    public Treadmill(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean obstace(Participant participant) {
        if (participant.jump(distance)) {
            //System.out.println(participant.toString() + " пробежал " + distance + "м.");
            return true;
        } else {
            //System.out.println(participant.toString() + " не смог пробежать " + distance + "м.");
            return false;
        }

    }
}

class Human implements Participant {
    private String name;
    public int canRun = 300;
    public int canJump = 2;

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Human " +
                name;
    }

    @Override
    public boolean run(int distance) {
        if (canRun >= distance) {
            System.out.println(name + " пробежал " + distance + " метров.");
            return true;
        } else {
            System.out.println(name + " не смог пробежать " + distance + " метров.");
            return false;
        }
    }

    @Override
    public boolean jump(int height) {
        if (canJump >= height) {
            System.out.println(name + " перепрыгнул " + height + " метров.");
            return true;
        } else {
            System.out.println(name + " не смог перепрыгнуть " + height + " метров.");
            return false;
        }
    }
}
class Cat implements Participant {
    private String name;
    public int canRun = 400;
    public int canJump = 3;

    public Cat(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cat" +
                name ;
    }

    @Override
    public boolean run(int distance) {
        if (canRun >= distance) {
            System.out.println(name + " пробежал " + distance + " метров.");
            return true;
        } else {
            System.out.println(name + " не смог пробежать " + distance + " метров.");
            return false;
        }
    }

    @Override
    public boolean jump(int height) {
        if (canJump >= height) {
            System.out.println(name + " перепрыгнул " + height + " метров.");
            return true;
        } else {
            System.out.println(name + " не смог перепрыгнуть " + height + " метров.");
            return false;
        }
    }
}
class Robot implements Participant {
    private String name;
    public int canRun = 500;
    public int canJump = 4;

    public Robot(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Robot" +
                name;
    }

    @Override
    public boolean run(int distance) {
        if (canRun >= distance) {
            System.out.println(name + " пробежал " + distance + " метров.");
            return true;
        } else {
            System.out.println(name + " не смог пробежать " + distance + " метров.");
            return false;
        }
    }

    @Override
    public boolean jump(int height) {
        if (canJump >= height) {
            System.out.println(name + " перепрыгнул " + height + " метров.");
            return true;
        } else {
            System.out.println(name + " не смог перепрыгнуть " + height + " метров.");
            return false;
        }
    }
}


