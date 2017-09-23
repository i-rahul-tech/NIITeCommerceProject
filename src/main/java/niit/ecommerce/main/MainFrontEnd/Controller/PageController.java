package niit.ecommerce.main.MainFrontEnd.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import niit.ecommerce.main.MainBackEnd.Dao.CartDao;
import niit.ecommerce.main.MainBackEnd.Dao.CartItemDao;
import niit.ecommerce.main.MainBackEnd.Dao.CategoryDao;
import niit.ecommerce.main.MainBackEnd.Dao.ProductDao;
import niit.ecommerce.main.MainBackEnd.Dao.UserDao;
import niit.ecommerce.main.MainBackEnd.dto.Cart;
import niit.ecommerce.main.MainBackEnd.dto.CartItem;
import niit.ecommerce.main.MainBackEnd.dto.Category;
import niit.ecommerce.main.MainBackEnd.dto.Product;
import niit.ecommerce.main.MainBackEnd.dto.User;

@Controller
public class PageController {

	@Autowired
	ProductDao productDao;

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	UserDao userDao;

	@Autowired
	CartItemDao cartItemDao;

	@Autowired
	CartDao cartDao;

	@RequestMapping(value = { "/", "/index", "/home" })
	public String index(Principal principal, Model model) {

		if (principal != null) {

			User user = userDao.getUserByUsername(principal.getName());
			if (user.getRole().equalsIgnoreCase("Admin")) {
				return "admin/adminIndex";
			} else if (user.getRole().equalsIgnoreCase("Customer")) {
				model.addAttribute("uemail", principal.getName());
				model.addAttribute("uname", user.getUfname());
				return "user/userindex";
			}
		}
		return "index";
	}

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("err", "Authentication Failed - Invalid Credentials!");
		}
		return "Login";
	}

	@RequestMapping(value = { "/login/user" })
	public String userlogin() {
		return "Login";
	}

	@RequestMapping(value = { "/Books", "/Electronics", "/Fashion", "/Appliances" })
	public ModelAndView dropForward(@RequestParam("name") String cat_name, Model map) {
		ModelAndView mv = new ModelAndView("product1");
		Category category = categoryDao.getCategoryBySubCategoryName(cat_name);
		List<Product> productlist = productDao.getActiveProductByCategory(category);
		map.addAttribute("prod_list", productlist);
		return mv;
	}

	@RequestMapping(value = { "/product/search" }, method = RequestMethod.GET)
	public ModelAndView searchProduct(@RequestParam("product_name") String pname, Model map, Principal principal) {
		if (principal != null) {
			User user = userDao.getUserByUsername(principal.getName());
			map.addAttribute("uname", user.getUfname());
		}
		ModelAndView mv = new ModelAndView("product1");
		List<Product> productList = productDao.getProductByProductName(pname);
		mv.addObject("prod_list", productList);
		return mv;
	}

	@RequestMapping("view/product")
	public ModelAndView viewProduct(Model map) {
		ModelAndView mv = new ModelAndView("product1");
		List<Product> listproduct = productDao.getActiveProducts();
		map.addAttribute("prod_list", listproduct);
		return mv;
	}

	@RequestMapping(value = { "/trending" })
	public ModelAndView prodisplay(@RequestParam("pid") Long pid, Model map, Principal principal) {
		if (principal != null) {
			User user = userDao.getUserByUsername(principal.getName());
			map.addAttribute("uname", user.getUfname());
		}
		ModelAndView mv = new ModelAndView("productdisplay");
		Product product = productDao.getProductByProductId(pid);
		map.addAttribute("product", product);
		return mv;
	}

	@RequestMapping("/user/usercart")
	public String viewCart(Principal principal, Model map) {
		if (principal != null) {
			User user = userDao.getUserByUsername(principal.getName());
			Cart cart = user.getCart();
			List<CartItem> cartlist = cartItemDao.cartItemGetByCart(cart);
			map.addAttribute("uname", user.getUfname());
			map.addAttribute("cartlist", cartlist);
			map.addAttribute("total", cart.getGrandTotal());
			return "user/usercart";
		}
		return "user/usercart";
	}

	@RequestMapping("/view/usercart")
	public String toViewCart(Model map, Principal principal) {
		if (principal != null) {
			User user = userDao.getUserByUsername(principal.getName());
			map.addAttribute("uname", user.getUfname());
		}
		return "user/usercart";
	}

	@RequestMapping("addtocart")
	public String addToCart(@RequestParam("pid") Long pid, Principal principal, Model map, HttpServletRequest request) {
		if (principal == null) {
			return "Login";
		} else {
			String referer = request.getHeader("Referer");
			User user = userDao.getUserByUsername(principal.getName());
			Cart cart = user.getCart();
			Product product = productDao.getProductByProductId(pid);
			CartItem cartitem = cartItemDao.getCartItemByCartIdAndProductId(cart, product);
			if (cartitem == null) {
				cartitem = new CartItem();
				cartitem.setCart(cart);
				cartitem.setProduct(product);
				cartitem.setSell_quantity(1);
				cartitem.setTotal_price(product.getPrice());
				Boolean b = cartItemDao.addCartItem(cartitem);
				if (b) {
					map.addAttribute("msg", "Product " + product.getProd_name() + " Added To Cart");
					cartitem = cartItemDao.getCartItemByCartIdAndProductId(cart, product);
					cart.setGrandTotal(cartItemDao.getGrandTotal(cart));
					cart.setCartItemCount(cartItemDao.getTotalQuantity(cart));
					cartDao.updateCart(cart);
					return "redirect:" + referer;
				} else {
					map.addAttribute("msg", "Something Went Wrong.....");
					return "redirect:" + referer;
				}
			} else {
				int newquant = cartitem.getSell_quantity() + 1;
				cartitem.setSell_quantity(newquant);
				cartitem.setTotal_price(product.getPrice() * newquant);
				Boolean b = cartItemDao.updateCartItem(cartitem);
				if (b) {
					map.addAttribute("msg", "One More Quantity Of " + product.getProd_name() + " Added To Cart");
					cart.setGrandTotal(cartItemDao.getGrandTotal(cart));
					cart.setCartItemCount(cartItemDao.getTotalQuantity(cart));
					cartDao.updateCart(cart);
					return "redirect:" + referer;
				} else {
					map.addAttribute("msg", "Ohhh...Something Went Wrong...Try Adding From Cart");
					return "redirect:" + referer;
				}
			}
		}
	}

	@RequestMapping(value = { "/refreshcart" }, method = RequestMethod.GET)
	public String refreshcart(@RequestParam("quantity") int quan, Model map, @RequestParam("pid") Long pid,
			Principal principal, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		User user = userDao.getUserByUsername(principal.getName());
		Cart cart = user.getCart();
		Product product = productDao.getProductByProductId(pid);
		CartItem cartitem = cartItemDao.getCartItemByCartIdAndProductId(cart, product);
		cartitem.setSell_quantity(quan);
		cartitem.setTotal_price(product.getPrice() * quan);
		Boolean b = cartItemDao.updateCartItem(cartitem);
		if (b) {
			map.addAttribute("msg", "Product Updated To Cart");
			cart.setGrandTotal(cartItemDao.getGrandTotal(cart));
			cart.setCartItemCount(cartItemDao.getTotalQuantity(cart));
			cartDao.updateCart(cart);
			return "redirect:" + referer;
		} else {
			map.addAttribute("msg", "Error....Please Try Again Later");
			return "redirect:" + referer;
		}
	}

	@RequestMapping(value = { "/deletecartitem" })
	public String deleteSingleCartItem(@RequestParam("cartid") Long cid, Model map, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		CartItem cartitem = cartItemDao.getCartItemByCartItem_Id(cid);
		Cart cart = cartitem.getCart();
		Boolean b = cartItemDao.deleteCartItem(cid);
		if (b) {
			cart.setGrandTotal(cartItemDao.getGrandTotal(cart));
			cart.setCartItemCount(cartItemDao.getTotalQuantity(cart));
			cartDao.updateCart(cart);
			return "redirect:" + referer;
		}
		return "redirect:" + referer;
	}

	@RequestMapping(value = { "/user/updateinfo" }, method = RequestMethod.GET)
	public String toupdatepage(Principal principal, Model map) {
		User user = userDao.getUserByUsername(principal.getName());
		map.addAttribute("user", user);
		map.addAttribute("uname", user.getUfname());
		return "user/updateinfo";
	}

	@RequestMapping(value = { "/updateuserinfo" }, method = RequestMethod.POST)
	public String toUpdateUser(@RequestParam("dob") String dob, @RequestParam("contact") String contact,
			@RequestParam("address") String address, @RequestParam("state") String state,
			@RequestParam("pincode") String pincode, Principal principal, Model map, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		User user = userDao.getUserByUsername(principal.getName());

		user.setDob(dob);
		user.setContact(contact);
		user.setPincode(pincode);
		user.setAddress(address);
		user.setState(state);

		Boolean b = userDao.updateUser(user);
		if (b) {
			map.addAttribute("msg", "Your Information Have Been Successfuly Updated");
			return "redirect:" + referer;
		} else {
			map.addAttribute("msg", "Oops.......Something Went Wrong...Try Again Later...");
			return "redirect:" + referer;
		}
	}

	@RequestMapping(value = { "/user/updatepwd" })
	public String toupdatepwdpage(Principal principal, Model map) {
		User user = userDao.getUserByUsername(principal.getName());
		map.addAttribute("uname", user.getUfname());
		map.addAttribute("upwd", user.getPassword());
		return "user/updatepassword";
	}

	@RequestMapping(value = { "/updatepassword" },method = RequestMethod.POST)
	public String toUpdateUser(@RequestParam("oldpwd") String oldpwd, @RequestParam("newpwd") String newpwd,
			Principal principal, Model map, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		User user = userDao.getUserByUsername(principal.getName());

		if (user.getPassword().equals(oldpwd)) {
			user.setPassword(newpwd);
			Boolean b = userDao.updateUser(user);
			if (b) {
				map.addAttribute("msg", "Password Changed Successfully");
				return "redirect:" + referer;
			} else {
				map.addAttribute("msg", "Oops.......Something Went Wrong...Try Again Later...");
				return "redirect:" + referer;
			}
		}
		else {
			map.addAttribute("pname",user.getUfname());
			return "redirect:" + referer;
		}
	}

	@ModelAttribute("electronicsList")
	public List<Category> getElectronicList() {
		List<Category> electronicsList = categoryDao.getAllCategoryByCategoryName("Electronics");
		return electronicsList;
	}

	@ModelAttribute("appliancesList")
	public List<Category> getAppliancesList() {
		List<Category> appliancesList = categoryDao.getAllCategoryByCategoryName("Home Appliances");
		return appliancesList;
	}

	@ModelAttribute("fashionList")
	public List<Category> getFashionList() {
		List<Category> fashionList = categoryDao.getAllCategoryByCategoryName("Fashion");
		return fashionList;
	}

	@ModelAttribute("bookList")
	public List<Category> getBookList() {
		List<Category> bookList = categoryDao.getAllCategoryByCategoryName("Books");
		return bookList;
	}

	@ModelAttribute("proEightList")
	public List<Product> getproEightList() {
		List<Product> proEightList = productDao.select8Product();
		return proEightList;
	}
}
