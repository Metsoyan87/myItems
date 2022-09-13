package servlet;
import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/item/edit")
public class ItemEditServlet extends HttpServlet {

    private final UserManager userManager = new UserManager();
    private final ItemManager itemManager = new ItemManager();
    private final CategoryManager categoryManager = new CategoryManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        Item item = itemManager.getItemById(itemId);
        req.setAttribute("users", userManager.getAllUsers());
        req.setAttribute("item", item);
        req.getRequestDispatcher("/WEB-INF/editItem.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int itemId = Integer.parseInt(req.getParameter("itemId"));//
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        Item item = Item.builder()
                .id(itemId)
                .title(title)
                .price(price)
                .category(categoryManager.getCategoryById(categoryId))
                .user(userManager.getUserById(userId))
                .build();
        itemManager.editItem(item);
        resp.sendRedirect("/items");
    }
}
