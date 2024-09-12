package Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import DAO.PhonebookDAO;
import VO.PhonebookVO;
import java.util.List;

@org.springframework.stereotype.Controller
public class PhonebookController {

	    @Autowired
	    private PhonebookDAO phonebookDAO;

	    @GetMapping("/")
	    public String index(Model model) {
	        List<PhonebookVO> phonebookList = phonebookDAO.selectAll();
	        model.addAttribute("phonebookList", phonebookList);
	        return "index";  
	    }

	    @PostMapping("/insertPhonebook")
	    public String addPhonebook(@RequestParam("name") String name, 
	                               @RequestParam("hp") String hp, 
	                               @RequestParam("memo") String memo) {
	        PhonebookVO pb = new PhonebookVO();
	        pb.setName(name);
	        pb.setHp(hp);
	        pb.setMemo(memo);
	        
	        phonebookDAO.insert(pb);
	        return "redirect:/"; 
	    }

	    @GetMapping("/searchPhonebook")
	    public String searchPhonebook(@RequestParam(value = "search", required = false) String search, Model model) {
	        List<PhonebookVO> phonebookList;
	        if (search != null && !search.isEmpty()) {
	            phonebookList = phonebookDAO.search(search);
	        } else {
	            phonebookList = phonebookDAO.selectAll();
	        }
	        model.addAttribute("phonebookList", phonebookList);
	        return "index";
	    }

	    @GetMapping("/selectPhonebook")
	    public String selectPhonebook(@RequestParam("id") int id, Model model) {
	        PhonebookVO selectedEntry = phonebookDAO.selectById(id);
	        model.addAttribute("selectedEntry", selectedEntry);
	        return "index";
	    }

	    @PostMapping("/updatePhonebook")
	    public String updatePhonebook(@RequestParam("id") int id, 
	                                  @RequestParam("name") String name, 
	                                  @RequestParam("hp") String hp, 
	                                  @RequestParam("memo") String memo) {
	        PhonebookVO pb = new PhonebookVO();
	        pb.setId(id);
	        pb.setName(name);
	        pb.setHp(hp);
	        pb.setMemo(memo);
	        
	        phonebookDAO.update(pb);
	        return "redirect:/";  // 수정 후 홈으로 리다이렉트
	    }

	    // 전화번호 삭제 (delete)
	    @PostMapping("/deletePhonebook")
	    public String deletePhonebook(@RequestParam("id") int id) {
	        phonebookDAO.delete(id);
	        return "redirect:/";  // 삭제 후 홈으로 리다이렉트
	    }
	

}