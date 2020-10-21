package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        //Taskのインスタンスを作成
        Task t = new Task();

        //各フィールドの値をセット
        String content = "退勤";
        t.setContent(content);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis() ); //currentTimeMillis()で現在の秒数まで取得
        t.setCreated_at(currentTime);
        t.setUpdated_at(currentTime);

        //生成した t オブジェクトをpersistで管理下に置く
        em.persist(t);
        //DBに変更内容を保存
        em.getTransaction().commit();

        //自動採番されたIDの値を表示
        //getWriteによりPrintWriteオブジェクトとして返すことで表示する
        response.getWriter().append(Integer.valueOf(t.getId()).toString());

        em.close();
    }

}
