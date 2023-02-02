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


@WebServlet(urlPatterns = "/item/edit")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class ItemEditServlet extends HttpServlet {

    private final ItemManager itemManager = new ItemManager();
    private static final String IMAGE_PATH = "C:\\Users\\Mnac\\IdeaProjects\\myItems\\myItemsImages";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int itemId = Integer.parseInt(req.getParameter("itemId"));
        Item item = itemManager.getItemById(itemId);

        req.setAttribute("item", item);
        req.getRequestDispatcher("/WEB-INF/editItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        Part profilePicPart = req.getPart("profilePic");

        String fileName = null;
        if (profilePicPart != null) {
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + profilePicPart.getSubmittedFileName();
            profilePicPart.write(IMAGE_PATH + fileName);
        }

        Item item = Item.builder()
                .id(itemId)
                .title(title)
                .price(price)
                .profilePic(fileName)

                .build();
        itemManager.editItem(item);
        resp.sendRedirect("/items");
    }
}
