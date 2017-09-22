package niit.ecommerce.main.MainFrontEnd.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import niit.ecommerce.main.MainBackEnd.Dao.CategoryDao;
import niit.ecommerce.main.MainBackEnd.Dao.ProductDao;
import niit.ecommerce.main.MainBackEnd.Dao.UserDao;
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
	
	@RequestMapping(value = { "/", "/index", "/home" })
	public String index(Principal principal, Model model) {
		if (principal != null)
		{
			
			User user = userDao.getUserByUsername(principal.getName());
			if(user.getRole().equalsIgnoreCase("Admin")){
				return "admin/adminIndex";
			}
			else if(user.getRole().equalsIgnoreCase("Customer"))
			{
				model.addAttribute("uemail",principal.getName());
				model.addAttribute("uname",user.getUfname());
				return "user/userindex";
			}
		}
		return "index";
	}
	
	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false)String error, Model model){
		if(error != null){
			model.addAttribute("err", "Authentication Failed - Invalid Credentials!");
		}
		return "Login";
	} 
	
	@RequestMapping(value = {"/login/user"})
	public String userlogin()
	{
		return "Login";
	}
	
	@RequestMapping(value = {"/Books","/Electronics","/Fashion","/Appliances","/view/Electronics"})
	public ModelAndView dropForward(@RequestParam("name") String cat_name, Model map)
	{
		ModelAndView mv = new ModelAndView("test");
		map.addAttribute("cate_name", cat_name);
		return mv;
	}
	
	@RequestMapping(value = {"/product/search"}, method = RequestMethod.GET)
	public ModelAndView searchProduct(@RequestParam("product_name") String pname, Model map, Principal principal)
	{
		if(principal!=null)
		{
			User user = userDao.getUserByUsername(principal.getName());
			map.addAttribute("uname",user.getUfname());
		}
		ModelAndView mv = new ModelAndView("product1");
		List<Product> productList = productDao.getProductByProductName(pname);
		mv.addObject("prod_list", productList);
		return mv;
	}
	
	@RequestMapping("view/product")
	public ModelAndView viewProduct(Model map)
	{
		ModelAndView mv = new ModelAndView("product1");
		List<Product> listproduct = productDao.getActiveProducts();
		map.addAttribute("prod_list",listproduct);
		return mv;
	}
	
	@RequestMapping(value = {"/trending"})
	public ModelAndView prodisplay(@RequestParam("pid") Long pid, Model map)
	{
		ModelAndView mv = new ModelAndView("productdisplay");
		Product product = productDao.getProductByProductId(pid);
		map.addAttribute("product",product);
		return mv;
	}
	
	@RequestMapping("/user/usercart")
	public String viewCart(Principal p)
	{
			return "user/usercart";
	}
	
	@ModelAttribute("electronicsList")
	   public List<Category> getElectronicList()
	   {
	     List<Category> electronicsList = categoryDao.getAllCategoryByCategoryName("Electronics");
	     return electronicsList;
	   }
	
	@ModelAttribute("appliancesList")
	   public List<Category> getAppliancesList()
	   {
	     List<Category> appliancesList = categoryDao.getAllCategoryByCategoryName("Home Appliances");
	     return appliancesList;
	   }
	
	@ModelAttribute("fashionList")
	   public List<Category> getFashionList()
	   {
	     List<Category> fashionList = categoryDao.getAllCategoryByCategoryName("Fashion");
	     return fashionList;
	   }
	
	@ModelAttribute("bookList")
	   public List<Category> getBookList()
	   {
	     List<Category> bookList = categoryDao.getAllCategoryByCategoryName("Books");
	     return bookList;
	   }
	
	@ModelAttribute("proEightList")
	   public List<Product> getproEightList()
	   {
	     List<Product> proEightList = productDao.select8Product();
	     return proEightList;
	   }
}
