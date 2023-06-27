import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().
                configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        /*LOGGER.info("Заполнение таблиц goods и other_goods");
        TableFilling.goodsFilling(session);
        LOGGER.info("Заполнение таблиц users и user_credentials");
        TableFilling.usersFilling(session);
        LOGGER.info("Заполнение таблиц orders и other_orders");
        TableFilling.orderFilling(session);
        LOGGER.info("Заполнение таблицы other_baskets");
        TableFilling.otherBasketFilling(session);
        LOGGER.info("Заполнение таблицы baskets");
        TableFilling.basketFilling(session);*/

//        String hql = "FROM " + OtherBasket.class.getSimpleName();
//        ArrayList<OtherBasket> otherBasketsList = (ArrayList<OtherBasket>) session.createSelectionQuery(hql).getResultList();
//        OtherBasket othBask = otherBasketsList.get(0) ;
//
//        System.out.println(othBask.getOtherBasketKey().getGoodId() + " - "
//                + othBask.getOtherBasketKey().getOrderId() + " - "
//                + othBask.getGoodsCount());

//        String hql2 = "FROM " + OtherGood.class.getSimpleName();
//        ArrayList<OtherGood> otherGoodsList = (ArrayList<OtherGood>) session.createSelectionQuery(hql2).getResultList();
//        OtherGood othGood = otherGoodsList.get(0) ;

//        System.out.println(othGood.getId() + " - " + othGood.getName() + " - " + othGood.getPrice());

        String hql3 = "FROM " + User.class.getSimpleName();
        ArrayList<User> UsersList = (ArrayList<User>) session.createSelectionQuery(hql3).getResultList();
        User user = UsersList.get(2) ;

        System.out.println(user.getId() + " - " + user.getName() + " - " + user.getRegDate());

//        othBask.setGoodsCount(19);
//
//        System.out.println(othBask.getOtherBasketKey().getGoodId() + " - "
//                + othBask.getOtherBasketKey().getOrderId() + " - "
//                + othBask.getGoodsCount());

        session.beginTransaction();
//        Удалилась только одна корзина
//        session.remove(othBask);
//        При cascade.ALL удалились все корзины и заказы, в которых был этот товар. Если в заказ или корзина были
//                и другие товары, то соответственно они тоже удалились :( Короче исчезла большая часть из 3-х таблиц.
//        session.remove(othGood);

        session.remove(user);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

    }


}
