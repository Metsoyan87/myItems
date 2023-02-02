package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Category;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;



@WebServlet(urlPatterns = "/items/add")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class AddItemServlet extends HttpServlet {
    private final ItemManager itemManager = new ItemManager();
    private final CategoryManager categoryManager = new CategoryManager();
    private final UserManager userManager = new UserManager();
    private static final String IMAGE_PATH = "C:\\Users\\Mnac\\IdeaProjects\\myItems\\myItemsImages";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryManager.getAllCategories());

        req.getRequestDispatcher("/WEB-INF/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String title = req.getParameter("title");
        String price1 = req.getParameter("price");
        double price = Double.parseDouble(price1);
        int catId = Integer.parseInt(req.getParameter("category_id"));
        Category category = categoryManager.getCategoryById(catId);
        Part profilePicPart = req.getPart("profilePic");

        String fileName = null;
        if (profilePicPart != null) {
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + profilePicPart.getSubmittedFileName();
            profilePicPart.write(IMAGE_PATH + fileName);
        }
        Item item = Item.builder()
                .title(title)
                .price(price)
                .category(category)
                .profilePic(fileName)
                .user(user)
                .build();
        itemManager.addItem(item);
        resp.sendRedirect("/home");
    }
}
