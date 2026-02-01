package nhom6.Buithanhanhvu.controllers;

import nhom6.Buithanhanhvu.daos.Item;
import nhom6.Buithanhanhvu.services.BookService;
import nhom6.Buithanhanhvu.services.CartService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final CartService cartService;

    public BookController(BookService bookService, CartService cartService) {
        this.bookService = bookService;
        this.cartService = cartService;
    }

    @GetMapping
    public String showAllBooks(
               @NotNull Model model,
               @RequestParam(defaultValue = "0") Integer pageNo,
               @RequestParam(defaultValue = "20") Integer pageSize,
               @RequestParam(defaultValue = "id") String sortBy) {
        model.addAttribute("books", bookService.getAllBooks(pageNo, pageSize, sortBy));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", bookService.getAllBooks(pageNo, pageSize, sortBy).size() / pageSize);
        return "book/list";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(HttpSession session,
                            @RequestParam long id,
                            @RequestParam String name,
                            @RequestParam double price,
                            @RequestParam(defaultValue = "1") int quantity) {
        var cart = cartService.getCart(session);
        cart.addItems(new Item(id, name, price, quantity));
        cartService.updateCart(session, cart);
        return "redirect:/books";
    }

    @GetMapping("/cart")
    public String showCart(HttpSession session, Model model) {
        model.addAttribute("cart", cartService.getCart(session));
        model.addAttribute("totalPrice", cartService.getSumPrice(session));
        model.addAttribute("totalQuantity", cartService.getSumQuantity(session));
        return "book/cart";
    }

    @PostMapping("/remove-from-cart")
    public String removeFromCart(HttpSession session, @RequestParam Long id) {
        var cart = cartService.getCart(session);
        cart.removeItems(id);
        cartService.updateCart(session, cart);
        return "redirect:/books/cart";
    }

    @PostMapping("/update-cart")
    public String updateCart(HttpSession session,
                            @RequestParam Long id,
                            @RequestParam int quantity) {
        var cart = cartService.getCart(session);
        cart.updateItems(id.intValue(), quantity);
        cartService.updateCart(session, cart);
        return "redirect:/books/cart";
    }

    @PostMapping("/clear-cart")
    public String clearCart(HttpSession session) {
        cartService.removeCart(session);
        return "redirect:/books/cart";
    }
}