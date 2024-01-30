package src;

import java.util.Scanner;
import java.util.InputMismatchException;

public class App {
    private static User user[] = new User[100];
    private static int userIndex = 0;

    public static void menu() {
        System.out.println("");
        System.out.println("欢迎来到图书管理系统！");
        System.out.println("1. 登录");
        System.out.println("2. 注销");
        System.out.println("3. 借书");
        System.out.println("4. 还书");
        System.out.println("5. 查看当前所有用户");
        System.out.println("0. 退出");
        System.out.printf("请输入您的选择：");

        try (Scanner sc = new Scanner(System.in)) {
            int choice;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("输入错误，请重新输入！");
                try {
                    Thread.sleep(2000); // 暂停2秒钟
                } catch (InterruptedException ea) {
                    ea.printStackTrace();
                }
                menu();
                return;
            }

            switch (choice) {
                case 1: // 登录
                    System.out.println("请输入用户名：");
                    String newName = sc.next();
                    user[userIndex] = new User(newName);
                    userIndex++;
                    System.out.println("登录成功！");
                    try {
                        Thread.sleep(2000); // 暂停2秒钟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    menu();
                    break;
                case 2: // 注销
                    System.out.println("请输入用户名：");
                    String oldName = sc.next();
                    for(int i = 0; i < userIndex; i++) {
                        if(user[i].getName().equals(oldName)) {
                            user[i] = null;
                            System.out.println("注销成功！");
                            try {
                                Thread.sleep(2000); // 暂停2秒钟
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            menu();
                            return;
                        }
                    }
                    System.out.println("用户不存在！");
                    try {
                        Thread.sleep(2000); // 暂停2秒钟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    menu();
                    break;
                case 3: // 借书
                    
                    break;
                case 4: // 还书
                    
                    break;
                case 0: // 退出
                    System.out.println("再见！");
                    try {
                        Thread.sleep(1000); // 暂停1秒钟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入错误，请重新输入！");
                    try {
                        Thread.sleep(2000); // 暂停2秒钟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    menu();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        menu();
    }
}