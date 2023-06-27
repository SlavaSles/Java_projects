import jakarta.persistence.ManyToMany;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TableFilling {
    private static final Logger LOGGER = LogManager.getLogger(TableFilling.class);
    public static void goodsFilling(Session session) {
        Good[] goods = new Good[10];
        OtherGood[] otherGoods = new OtherGood[10];
        goods[0] = new Good("Масло", 80, 140);
        goods[1] = new Good("Хлеб", 100, 40);
        goods[2] = new Good("Сахар", 50, 80);
        goods[3] = new Good("Соль", 70, 25);
        goods[4] = new Good("Сыр", 60, 190);
        goods[5] = new Good("Молоко", 90, 80);
        goods[6] = new Good("Йогурт", 70, 50);
        goods[7] = new Good("Шоколад", 120, 60);
        goods[8] = new Good("Лимонад", 80, 75);
        goods[9] = new Good("Вода", 40, 40);
        for (int i = 0; i < goods.length; i++) {
            otherGoods[i] = new OtherGood(goods[i].getName(), goods[i].getCount(), goods[i].getPrice());
        }
        Transaction transaction = session.beginTransaction();
        for (int i = 0; i < goods.length; i++) {
            session.persist(goods[i]);
            session.persist(otherGoods[i]);
        }
        transaction.commit();
    }

    public static void usersFilling(Session session) {
        User[] users = new User[7];
        UserCredential[] usersCredentials = new UserCredential[7];
        LocalDateTime regDate = LocalDateTime.of(LocalDate.of(2019, 02, 17), LocalTime.of(17, 15, 10));
        users[0] = new User("Иванов Иван Иванович", regDate);
        usersCredentials[0] = new UserCredential(users[0], "ivanov_i", "ivanivan");
        users[0].setUserCredential(usersCredentials[0]);

//        regDate = LocalDateTime.of(LocalDate.of(2019, 02, 17), LocalTime.of(17, 15, 10));
        users[1] = new User("Петров Петр Петрович", LocalDateTime.of(LocalDate.of(2019, 05, 12), LocalTime.of(9, 10, 12)));
        usersCredentials[1] = new UserCredential(users[1], "petrov_p", "petrpetr");
        users[1].setUserCredential(usersCredentials[1]);

        regDate = LocalDateTime.of(LocalDate.of(2021, 06, 7), LocalTime.of(14, 40, 50));
        users[2] = new User("Сидоров Сидор Сидорович", regDate);
        usersCredentials[2] = new UserCredential(users[2], "sidorov_s", "sidorsidor");
        users[2].setUserCredential(usersCredentials[2]);

        regDate = LocalDateTime.of(LocalDate.of(2020, 03, 12), LocalTime.of(11, 07, 15));
        users[3] = new User("Николаев Николай Николаевич", regDate);
        usersCredentials[3] = new UserCredential(users[3], "nikolaev_n", "nikoniko");
        users[3].setUserCredential(usersCredentials[3]);

        regDate = LocalDateTime.of(LocalDate.of(2019, 10, 5), LocalTime.of(16, 45, 10));
        users[4] = new User("Александров Александр Александрович", regDate);
        usersCredentials[4] = new UserCredential(users[4], "alexandrov_a", "alexalex");
        users[4].setUserCredential(usersCredentials[4]);

        regDate = LocalDateTime.of(LocalDate.of(2020, 9, 2), LocalTime.of(18, 25, 0));
        users[5] = new User("Григорьев Григорий Григорьевич", regDate);
        usersCredentials[5] = new UserCredential(users[5], "grigoriev_g", "grigri");
        users[5].setUserCredential(usersCredentials[5]);

        regDate = LocalDateTime.of(LocalDate.of(2021, 01, 22), LocalTime.of(10, 00, 10));
        users[6] = new User("Кириллов Кирилл Кириллович", regDate);
        usersCredentials[6] = new UserCredential(users[6], "kirillov_k", "kiryakirya");
        users[6].setUserCredential(usersCredentials[6]);

        Transaction transaction = session.beginTransaction();
        for (int i = 0; i < users.length; i++) {
            session.persist(users[i]);
        }
        transaction.commit();

    }

    public static void orderFilling(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> userQuery = builder.createQuery(User.class);
        Root<User> root0 = userQuery.from(User.class);
        userQuery.select(root0);
        ArrayList<User> UsersList = (ArrayList<User>) session.createQuery(userQuery).getResultList();
        Order[] orders = new Order[12];
        OtherOrder[] otherOrders = new OtherOrder[12];
        LocalDateTime regDate = LocalDateTime.of(LocalDate.of(2023, 01, 17), LocalTime.of(19, 15, 10));
        orders[0] = new Order(UsersList.get(0), OrderStatus.NEW, regDate);

        regDate = LocalDateTime.of(LocalDate.of(2023, 02, 13), LocalTime.of(10, 45, 30));
        orders[1] = new Order(UsersList.get(2), OrderStatus.CANCELLED, regDate);

        regDate = LocalDateTime.of(LocalDate.of(2023, 03, 7), LocalTime.of(19, 35, 30));
        orders[2] = new Order(UsersList.get(1), OrderStatus.DELIVERED, regDate);

        regDate = LocalDateTime.of(LocalDate.of(2023, 02, 02), LocalTime.of(22, 45, 30));
        orders[3] = new Order(UsersList.get(5), OrderStatus.PAYED, regDate);

        regDate = LocalDateTime.of(LocalDate.of(2023, 01, 30), LocalTime.of(15, 10, 30));
        orders[4] = new Order(UsersList.get(6), OrderStatus.PACKED, regDate);

        regDate = LocalDateTime.of(LocalDate.of(2023, 02, 4), LocalTime.of(13, 40, 30));
        orders[5] = new Order(UsersList.get(2), OrderStatus.NEW, regDate);

        regDate = LocalDateTime.of(LocalDate.of(2023, 01, 19), LocalTime.of(16, 5, 30));
        orders[6] = new Order(UsersList.get(3), OrderStatus.CANCELLED, regDate);

        regDate = LocalDateTime.of(LocalDate.of(2023, 02, 27), LocalTime.of(8, 45, 30));
        orders[7] = new Order(UsersList.get(5), OrderStatus.DELIVERED, regDate);

        regDate = LocalDateTime.of(LocalDate.of(2023, 03, 19), LocalTime.of(10, 25, 30));
        orders[8] = new Order(UsersList.get(0), OrderStatus.PAYED, regDate);

        regDate = LocalDateTime.of(LocalDate.of(2023, 02, 18), LocalTime.of(20, 40, 30));
        orders[9] = new Order(UsersList.get(1), OrderStatus.NEW, regDate);

        regDate = LocalDateTime.of(LocalDate.of(2023, 03, 03), LocalTime.of(10, 20, 15));
        orders[10] = new Order(UsersList.get(2), OrderStatus.PACKED, regDate);

        regDate = LocalDateTime.of(LocalDate.of(2023, 02, 14), LocalTime.of(12, 45, 00));
        orders[11] = new Order(UsersList.get(6), OrderStatus.DELIVERED, regDate);
//        orders[11].setId(24);

        for (int i = 0; i < orders.length; i++) {
            otherOrders[i] = new OtherOrder(orders[i].getUser(), orders[i].getOrderStatus(), orders[i].getCreationDate());
        }

        CriteriaQuery<OtherGood> goodQuery = builder.createQuery(OtherGood.class);
        Root<OtherGood> root1 = goodQuery.from(OtherGood.class);
        goodQuery.select(root1);
        ArrayList<OtherGood> otherGoodsList = (ArrayList<OtherGood>) session.createQuery(goodQuery).getResultList();
//        CriteriaQuery<OtherOrder> orderQuery = builder.createQuery(OtherOrder.class);
//        Root<OtherOrder> root2 = orderQuery.from(OtherOrder.class);
//        orderQuery.select(root2);
//        ArrayList<OtherOrder> otherOrdersList = (ArrayList<OtherOrder>) session.createQuery(orderQuery).getResultList();
//        int lastOrder = -1;
//        if (otherOrdersList.size() > 0) {
//            lastOrder = otherOrdersList.get(otherOrdersList.size() - 1).getId();
//        }
//        for (Order order : ordersList) {
//            if (order.getId() <= lastOrderIdInBasket) {
//                continue;
//            }
        List<OtherGood> goodsToOrder = new ArrayList<>();

        for (int i = 0; i < otherOrders.length; i++) {
            int goodTypesCount = (int) (Math.random() * 3) + 1;
            List<Integer> goodIds = new ArrayList<>();
            for (int j = 0; j < goodTypesCount; j++) {
                do {
                    int newGood = (int) (Math.random() * otherGoodsList.size());
                    if (!goodIds.contains(newGood)) {
                        goodIds.add(newGood);
                        break;
                    }
                }
                while (true);
            }
            Collections.sort(goodIds);
            for (int j = 0; j < goodIds.size(); j++) {
                goodsToOrder.add(otherGoodsList.get(goodIds.get(j)));
            }
            List<OtherGood> orderGoods = new ArrayList<>();
            orderGoods.addAll(goodsToOrder);
            otherOrders[i].setOtherGoods(orderGoods);
            goodsToOrder.clear();
        }

        try {
            session.beginTransaction();
            for (int i = 0; i < orders.length; i++) {
                session.persist(orders[i]);
                session.persist(otherOrders[i]);
            }
            LOGGER.info("Сохранение заказов Order и OtherOrder завершено");
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
//            Не работает rollback. При ошибке сохранения объектов счетчик id продолжает счет не с последнего числа, а с учетом неудачной транзакции
            session.getTransaction().rollback();
        }
        LOGGER.info("Транзакция завершена");
    }

    public static void otherBasketFilling(Session session) {
        LOGGER.info("Запрос из базы данных списка товаров OtherGoods");
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OtherGood> goodQuery = builder.createQuery(OtherGood.class);
        Root<OtherGood> root1 = goodQuery.from(OtherGood.class);
        goodQuery.select(root1);
        ArrayList<OtherGood> otherGoodsList = (ArrayList<OtherGood>) session.createQuery(goodQuery).getResultList();
        LOGGER.info("Запрос из базы данных списка заказов OtherOrders");
        CriteriaQuery<OtherOrder> otherOrderQuery = builder.createQuery(OtherOrder.class);
        Root<OtherOrder> root3 = otherOrderQuery.from(OtherOrder.class);
        otherOrderQuery.select(root3);
        ArrayList<OtherOrder> otherOrderList = (ArrayList<OtherOrder>) session.createQuery(otherOrderQuery).getResultList();

        for (OtherGood otherGood : otherGoodsList) {
            List<OtherOrder> otherOrdersToGoodList = new ArrayList<>();
            for (OtherOrder otherOrder : otherOrderList) {
                if (otherOrder.getOtherGoods().contains(otherGood)) {
                    otherOrdersToGoodList.add(otherOrder);
                }
            }
            List<OtherOrder> goodOrders = new ArrayList<>();
            goodOrders.addAll(otherOrdersToGoodList);
            otherGood.setOtherOrders(goodOrders);
            otherOrdersToGoodList.clear();
        }

        try {
            session.beginTransaction();
            otherGoodsList.forEach(othgood -> session.persist(othgood));
            LOGGER.info("Сохранение товаров OtherGood со списками OtherOrders завершено");
            session.getTransaction().commit();
            LOGGER.info("Транзакция завершена");
        } catch (Exception ex) {
            ex.printStackTrace();
//            Не работает rollback. При ошибке сохранения объектов счетчик id продолжает счет не с последнего числа, а с учетом неудачной транзакции
            session.getTransaction().rollback();
        }

        LOGGER.info("Запрос из базы данных списка корзин OtherBaskets");
        CriteriaQuery<OtherBasket> otherBasketQuery = builder.createQuery(OtherBasket.class);
        Root<OtherBasket> root2 = otherBasketQuery.from(OtherBasket.class);
        otherBasketQuery.select(root2);
        ArrayList<OtherBasket> otherBasketList = (ArrayList<OtherBasket>) session.createQuery(otherBasketQuery).getResultList();
//        Не удалось сделать генерацию количества товаров в корзине, неизменной между запусками программы,
//        т. к. при привязке к товарам списка заказов таблица OtherBasket обновляется целиком и из нее стираются
//        все данные о количестве товаров с предыдущей генерации. Причина двусторонние жесткие связи между классами
//        OtherOrder и OtherGood. Создать оба списка за раз нельзя, т. к. до сохранения в базу у них нет Id.
//        Т. е. для связывающих таблиц ManyToMany лучше не делать дополнительные поля.
        for (OtherBasket otherBasket : otherBasketList) {
            if (otherBasket.getGoodsCount() == null) {
                do {
                    int basketGoodCount = (int) (Math.random() * 99) + 1;
                    int goodId = otherBasket.getOtherBasketKey().getGoodId();
                    Integer[] goodsCount = otherGoodsList.stream().filter(good -> good.getId() == goodId).
                            map(good -> good.getCount()).toArray(Integer[]::new);
                    if (basketGoodCount <= goodsCount[0]) {
                        otherBasket.setGoodsCount(basketGoodCount);
                        break;
                    }
                } while (true);
            }
        }

        try {
            session.beginTransaction();
            otherBasketList.forEach(othBask -> session.merge(othBask));
            LOGGER.info("Сохранение корзин OtherBasket завершено");
            session.getTransaction().commit();
            LOGGER.info("Транзакция завершена");
        } catch (Exception ex) {
            ex.printStackTrace();
//            Не работает rollback. При ошибке сохранения объектов счетчик id продолжает счет не с последнего числа, а с учетом неудачной транзакции
            session.getTransaction().rollback();
        }
    }

    public static void basketFilling(Session session) {
        String hql1 = "FROM " + Good.class.getSimpleName();
        ArrayList<Good> goodsList = (ArrayList<Good>) session.createSelectionQuery(hql1).getResultList();
        String hql2 = "FROM " + Order.class.getSimpleName();
        ArrayList<Order> ordersList = (ArrayList<Order>) session.createSelectionQuery(hql2).getResultList();
        String hql3 = "FROM " + Basket.class.getSimpleName();
        ArrayList<Basket> basketsList = (ArrayList<Basket>) session.createSelectionQuery(hql3).getResultList();
//        try {
//            session.beginTransaction();
//            basketsList.forEach(basket -> session.remove(basket));
//            session.getTransaction().commit();
//        } catch (Exception ex) {
//            ex.printStackTrace();
////            Не работает rollback. При ошибке сохранения объектов счетчик id продолжает счет не с последнего числа, а с учетом неудачной транзакции
//            session.getTransaction().rollback();
//        }
        int lastOrderIdInBasket = -1;
        if (basketsList.size() > 0) {
            lastOrderIdInBasket = basketsList.get(basketsList.size() - 1).getOrder().getId();
        }
        for (Order order : ordersList) {
            if (order.getId() <= lastOrderIdInBasket) {
                continue;
            }
            int goodTypesCount = (int) (Math.random() * 3) + 1;
            List<Integer> goodIds = new ArrayList<>();
            for (int j = 0; j < goodTypesCount; j++) {
                do {
                    int newGood = (int) (Math.random() * goodsList.size());
                    if (!goodIds.contains(newGood)) {
                        goodIds.add(newGood);
                        break;
                    }
                }
                while (true);
//                Collections.sort(goodIds);
                BasketKey basketKey = new BasketKey(order.getId(), goodIds.get(j) + 1);
                Good good = goodsList.get(goodIds.get(j));
                do {
                    int basketGoodCount = (int) (Math.random() * 99) + 1;
                    if (basketGoodCount <= good.getCount()) {
                        Basket basket = new Basket(basketKey, order, good, basketGoodCount);
                        basketsList.add(basket);
                        break;
                    }
                } while (true);

            }
        }
        try {
            session.beginTransaction();
//            for (Basket basket : basketsList) {
//                session.persist(basket);
//            }
            basketsList.forEach(basket -> session.merge(basket));
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
//            Не работает rollback. При ошибке сохранения объектов счетчик id продолжает счет не с последнего числа, а с учетом неудачной транзакции
            session.getTransaction().rollback();
        }
    }
}
