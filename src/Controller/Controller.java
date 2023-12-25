package Controller;

import Model.NewBox;
import View.View;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Controller {
    private List<String> toyList = new ArrayList<>();
    View view = new View();
    private List<NewBox> boxList = new ArrayList<>();


    public void menu() {
        Scanner in = new Scanner(System.in);
        boolean validInput = false;
        int operation = 0;

        while (!validInput) {
            try {
                view.viewMenu();
                String input = in.nextLine();

                if (!input.matches("\\d+")) {
                    System.out.println("Ошибка: Введите целое число для выбора операции.");
                } else {
                    operation = Integer.parseInt(input);
                    validInput = true;
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        switch (operation) {
            case 1:
                NewBox newBox = newBox();
                boxList.add(newBox);
                menu();
                break;

            case 2:
                showAllBoxes();
                menu();
                break;

            case 3:
                runGameMachine();
                menu();
                break;

            case 0:
                System.out.println("До свидания!");
                break;

            default:
                System.out.println("Неверный вариант, выберите снова");
                menu();
                break;
        }
    }

    public NewBox newBox() {
        int maxId = 0;
        for (NewBox box : boxList) {
            if (box.getBox_Id() > maxId) {
                maxId = box.getBox_Id();
            }
        }
        int nextAvailableId = maxId + 1;
        Scanner in = new Scanner(System.in);
        System.out.println("Введите название сундука:");
        String name = in.nextLine();

        int chance;
        boolean validChance = false;
        do {
            System.out.println("Введите шанс (от 0 до 100%):");
            while (!in.hasNextInt()) {
                System.out.println("Ошибка: Введите целое число.");
                in.next();
            }
            chance = in.nextInt();
            if (chance >= 0 && chance <= 100) {
                validChance = true;
            } else {
                System.out.println("Ошибка: Введите значение от 0 до 100.");
            }
        } while (!validChance);

        System.out.println("Введите количество сундуков:");
        int quantity_box = in.nextInt();

        NewBox newBox = new NewBox(nextAvailableId, name, quantity_box, chance);
        System.out.println("Сундук успешно создан с id " + nextAvailableId);

        return newBox;
    }

    public void showAllBoxes() {
        if (boxList.isEmpty()) {
            System.out.println("Список сундуков пуст");
        } else {
            System.out.println("Список всех сундуков:");
            for (NewBox box : boxList) {
                System.out.println("ID: " + box.getBox_Id() + ", Название: " + box.getName_box() + ", Количество: " + box.getQuantity_box() + ", Шанс: " + box.getChance() + "%");
            }
        }
    }



    public void runGameMachine() {
        if (boxList.isEmpty()) {
            System.out.println("Список сундуков пуст");
        } else {
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("GameResults.txt", true)))) {
                for (NewBox box : boxList) {
                    System.out.println("Розыгрыш для сундука с ID " + box.getBox_Id() + " (" + box.getName_box() + "):");
                    writer.println("Розыгрыш для сундука с ID " + box.getBox_Id() + " (" + box.getName_box() + "):");
                    int totalWon = 0;
                    for (int i = 0; i < box.getQuantity_box(); i++) {
                        int draw = new Random().nextInt(100);
                        if (draw < box.getChance()) {
                            totalWon++;
                        }
                    }
                    System.out.println("Выигранные сундуки: " + totalWon + " из " + box.getQuantity_box());
                    writer.println("Выигранные сундуки: " + totalWon + " из " + box.getQuantity_box());
                }
                System.out.println("Результаты розыгрыша успешно записаны в файл GameResults.txt");
            } catch (IOException e) {
                System.out.println("Ошибка записи в файл: " + e.getMessage());
            }
        }
    }
}