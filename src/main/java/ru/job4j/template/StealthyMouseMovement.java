package ru.job4j.template;

import java.awt.*;
import java.util.Random;

public class StealthyMouseMovement {
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            Random random = new Random();

            int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
            int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

            for (int i = 0; i < 20; i++) { // Двигаем мышь 100 раз
                // Получаем текущие координаты мыши
                Point currentPos = MouseInfo.getPointerInfo().getLocation();
                System.out.println(currentPos.toString());

                // Генерируем случайные координаты для нового положения мыши
                int newX = random.nextInt(50, 2000); // Случайное смещение по X от -25 до 25
                int newY =  random.nextInt(50, 2000); // Случайное смещение по Y от -25 до 25

                // Убеждаемся, что новые координаты находятся в пределах экрана
                newX = Math.max(0, Math.min(screenWidth - 1, newX));
                newY = Math.max(0, Math.min(screenHeight - 1, newY));

                // Перемещаем мышь к новым координатам с плавным движением
                smoothMove(robot, currentPos.x, currentPos.y, newX, newY);

                // Добавляем случайную задержку перед следующим движением
                Thread.sleep(random.nextInt(200) + 100); // от 100 до 300 миллисекунд
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Плавное перемещение мыши в хаотичном зигзагообразном направлении
    private static void smoothMove(Robot robot, int startX, int startY, int endX, int endY) {
        Random random = new Random();
        int totalSteps = random.nextInt(7, 80); // общее количество шагов

        // Вычисление изменений по X и Y, чтобы пройти весь путь
        double deltaX = endX - startX;
        double deltaY = endY - startY;

        // Начальные координаты
        double currentX = startX;
        double currentY = startY;

        for (int i = 0; i < totalSteps; i++) {
            // Генерация случайных смещений для зигзагообразного движения
            double offsetX = random.nextInt(10) - 5; // отклонение по X от -5 до 5
            double offsetY = random.nextInt(10) - 5; // отклонение по Y от -5 до 5

            // Расчет промежуточных координат
            currentX += deltaX / totalSteps + offsetX;
            currentY += deltaY / totalSteps + offsetY;

            // Перемещение мыши к промежуточным координатам
            robot.mouseMove((int) currentX, (int) currentY);

            // Рывковое движение с небольшой задержкой
            robot.delay(20 + random.nextInt(10)); // добавляем случайную задержку от 20 до 30 мс
        }

        // Обеспечиваем достижение конечной точки
        robot.mouseMove(endX, endY);
    }
}
