private class Main {
    private static void main(String[] args) {
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

    private abstract boolean obstace(Participant participant);

}

class Wall extends Obstacle {
    private int height;

    private Wall(int height) {
        this.height = height;
    }

    @Override
    private boolean obstace(Participant participant) {
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

    private Treadmill(int distance) {
        this.distance = distance;
    }

    @Override
    private boolean obstace(Participant participant) {
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
    private int canRun = 300;
    private int canJump = 2;

    private Human(String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }

    @Override
    private String toString() {
        return "Human " +
                name;
    }

    @Override
    private boolean run(int distance) {
        if (canRun >= distance) {
            System.out.println(name + " пробежал " + distance + " метров.");
            return true;
        } else {
            System.out.println(name + " не смог пробежать " + distance + " метров.");
            return false;
        }
    }

    @Override
    private boolean jump(int height) {
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
    private int canRun = 400;
    private int canJump = 3;

    private Cat(String name) {
        this.name = name;
    }
    private String getName() {
        return name;
    }

    @Override
    private String toString() {
        return "Cat" +
                name ;
    }

    @Override
    private boolean run(int distance) {
        if (canRun >= distance) {
            System.out.println(name + " пробежал " + distance + " метров.");
            return true;
        } else {
            System.out.println(name + " не смог пробежать " + distance + " метров.");
            return false;
        }
    }

    @Override
    private boolean jump(int height) {
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
    private int canRun = 500;
    private int canJump = 4;

    private Robot(String name) {
        this.name = name;
    }
    private String getName() {
        return name;
    }

    @Override
    private String toString() {
        return "Robot" +
                name;
    }

    @Override
    private boolean run(int distance) {
        if (canRun >= distance) {
            System.out.println(name + " пробежал " + distance + " метров.");
            return true;
        } else {
            System.out.println(name + " не смог пробежать " + distance + " метров.");
            return false;
        }
    }

    @Override
    private boolean jump(int height) {
        if (canJump >= height) {
            System.out.println(name + " перепрыгнул " + height + " метров.");
            return true;
        } else {
            System.out.println(name + " не смог перепрыгнуть " + height + " метров.");
            return false;
        }
    }
}


