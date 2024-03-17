package ru.job4j.kiss.fool;

public class Fool {
    private int startAt = 1;
    private String answer = "answer";
    private final IOInterface ioInterface;

    public static void main(String[] args) {
        Fool fool = new Fool(new IOConsole());
        fool.startGame();
    }

    public Fool(IOInterface ioInterface) {
        this.ioInterface = ioInterface;
    }

    public void startGame() {
        System.out.println("Игра FizzBuzz.");
        while (startAt < 100 && !"Finish".equals(answer)) {
            messages(true);
            startAt++;
            answer = ioInterface.input();
            messages(false);
            startAt++;
        }
        System.out.println("Finish");
    }

    private void messages(boolean isRobot) {
        if (isFizzBuzz()) {
            showMessage(isRobot, "FizzBuzz");
        } else if (isFizz()) {
            showMessage(isRobot, "Fizz");
        } else if (isBuzz()) {
            showMessage(isRobot, "Buzz");
        } else {
            if (isRobot) {
                ioInterface.output(String.valueOf(startAt));
            }
        }
    }

    private void restart() {
        ioInterface.output("Ошибка. Начинай снова.");
        startAt = 0;
    }

    private boolean isFizzBuzz() {
        return startAt % 3 == 0 && startAt % 5 == 0;
    }

    private boolean isFizz() {
        return startAt % 3 == 0;
    }

    private boolean isBuzz() {
        return startAt % 5 == 0;
    }

    private void showMessage(boolean isRobot, String message) {
        if (message == null) {
            throw new IllegalArgumentException("Получено пустое сообщение");
        }
        if (isRobot) {
            ioInterface.output(message);
        } else {
            if (!message.equals(answer)) {
                restart();
            }
        }
    }
}
