package src;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static final ArrayList<User> users = new ArrayList<>();
    private static final ArrayList<Book> books = new ArrayList<>();

    private static int userIndex = 0;
    private static int loggedIndex = 0;

    public static void default_menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("欢迎来到图书管理系统！");
            System.out.println("1. 登录");
            System.out.println("2. 注册");
            System.out.println("0. 退出");
            System.out.print("请输入您的选择：");

            int choice;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("输入错误，请重新输入！");
                sc.next(); // clear the invalid input
                pause();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    // 登录
                    System.out.println("请输入用户名：");
                    String name = sc.next();
                    if (name.equals("Admin")) {
                        System.out.print("检测到管理员登录，请输入密码：");
                        String password = sc.next();
                        if (password.equals("admin")) {
                            System.out.println("登录成功！");
                            pause();
                            admin_menu();
                            return;
                        } else {
                            System.out.println("密码错误！请重新输入！");
                            pause();
                        }
                    } else {
                        boolean userFound = false;
                        for (int i = 0; i < userIndex; i++) {
                            if (users.get(i).getName().equals(name)) {
                                System.out.println("登录成功！");
                                loggedIndex = i;
                                pause();
                                user_menu();
                                userFound = true;
                                break;
                            }
                        }
                        if (!userFound) {
                            System.out.println("用户不存在！");
                            pause();
                        }
                    }
                }
                case 2 -> {
                    // 注册
                    System.out.print("请输入用户名：");
                    String newName = sc.next();
                    boolean userExists = false;
                    for (int i = 0; i < userIndex; i++) {
                        if (users.get(i).getName().equals(newName)) {
                            System.out.println("用户已存在！请重新输入！");
                            pause();
                            userExists = true;
                            break;
                        }
                    }
                    if (!userExists) {
                        User newUser = new User(newName);
                        users.add(newUser);
                        userIndex++;
                        System.out.println("注册成功！");
                        pause();
                    }
                }
                case 0 -> {
                    // 退出
                    System.out.println("再见！");
                    pause();
                    System.exit(0);
                }
                default -> {
                    System.out.println("输入错误，请重新输入！");
                    pause();
                }
            }
        }
    }

    public static void user_menu() {
        System.out.println();
        System.out.println("欢迎，" + users.get(userIndex - 1).getName() + "！");
        System.out.println("1. 注销");
        System.out.println("2. 借书");
        System.out.println("3. 还书");
        System.out.println("4. 退出登录");
        System.out.println("0. 退出系统");
        System.out.print("请输入您的选择：");

        try (Scanner sc = new Scanner(System.in)) {
            int choice;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("输入错误，请重新输入！");
                pause();
                default_menu();
                return;
            }

            switch (choice) {
                case 1 -> {
                    // 注销
                    users.remove(loggedIndex);
                    userIndex--;
                    System.out.println("注销成功！");
                    pause();
                    default_menu();
                }
                case 2 -> {
                    // 借书
                    System.out.print("请输入要借的书名：");
                    String title = sc.next();
                    for (Book book : books) {
                        if (book.getTitle().equals(title)) {
                            if (book.isAvailable()) {
                                book.toBorrow(loggedIndex);
                                System.out.println("借书成功！");
                            } else {
                                System.out.println("该书已被借出！");
                            }
                            pause();
                            user_menu();
                            return;
                        }
                    }
                    System.out.println("暂无此书！");
                }
                case 3 -> {
                    // 还书
                    System.out.print("请输入要还的书名：");
                    String returnTitle = sc.next();
                    for (Book book : books) {
                        if (book.getTitle().equals(returnTitle)) {
                            if (book.getBorrowedBy() == loggedIndex) {
                                book.toReturn();
                                System.out.println("还书成功！");
                            } else {
                                System.out.println("您没有借过此书！");
                            }
                            pause();
                            user_menu();
                            return;
                        }
                    }
                    System.out.println("暂无此书！");
                    pause();
                    user_menu();
                }
            }
        }
    }

    public static void admin_menu() {
        System.out.println();
        System.out.println("欢迎！管理员");
        System.out.println("1. 退出管理员模式");
        System.out.println("2. 查看当前所有用户");
        System.out.println("3. 查看当前所有图书");
        System.out.println("4. 添加书籍");
        System.out.print("请输入你的选择：");

        try (Scanner sc = new Scanner(System.in)) {
            int choice;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("输入错误，请重新输入！");
                pause();
                default_menu();
                return;
            }

            switch (choice) {
                case 1 -> {
                    // 退出管理员模式
                    System.out.println("退出管理员模式！");
                    pause();
                    default_menu();
                }
                case 2 -> {
                    // 查看当前所有用户
                    System.out.println("当前所有用户：");
                    for (User user : users) {
                        System.out.println(user.getName());
                    }
                    pause();
                    admin_menu();
                }
                case 3 -> {
                    // 查看当前所有图书
                    System.out.println("当前所有图书：");
                    for (Book book : books) {
                        System.out.println(book.getTitle());
                    }
                    pause();
                    admin_menu();
                }
                case 4 -> {
                    // 添加书籍
                    System.out.print("请输入书名：");
                    String title = sc.next();
                    System.out.print("请输入作者：");
                    String author = sc.next();
                    System.out.print("请输入出版社：");
                    String publisher = sc.next();
                    Book newBook = new Book(title, author, publisher, true);
                    books.add(newBook);
                    System.out.println("添加成功！");
                    pause();
                    admin_menu();
                }
            }
        }
    }

    public static void main(String[] args) {
        default_menu();
    }

    private static void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("错误");
        }
    }

    public static ArrayList<Book> getBooks() {
        return books;
    }
}