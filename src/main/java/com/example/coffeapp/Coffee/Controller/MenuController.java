package com.example.coffeapp.Coffee.Controller;

import com.example.coffeapp.Coffee.Model.Additives.CoffeeAdditive;
import com.example.coffeapp.Coffee.Model.Additives.Type;
import com.example.coffeapp.Coffee.Model.Product.*;
import com.example.coffeapp.Coffee.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MenuController {
    @Value("${upload.path}")
    private String uploadPath;
    private File uploadDir;

    private final CoffeeService coffeeService;
    private final TeaService teaService;
    private final SnackService snackService;
    private final DessertService dessertService;
    private final SandwichService sandwichService;
    private final CoffeeAdditivesService coffeeAdditivesService;
    @GetMapping("/admin-menu")
    public String getAllMenu() {
        return "/Admin/Menu/admin-menu";
    }

    //Coffee

    @GetMapping("coffee-list")
    public String getCoffeeList(Model model) {
        List<Coffee>productList = coffeeService.findAll();
        model.addAttribute("product", productList);
        return "/Admin/Menu/coffee-list";
    }
    @GetMapping("coffee-update/{id}")
    public String coffeeEdit(@PathVariable("id") Long id, Model model) {
        Coffee coffee = coffeeService.findById(id);
        model.addAttribute("product", coffee);
        return "/Admin/Menu/coffee-card";
    }
    @GetMapping("coffee-create")
    public String newCoffeeCreate(Model model) {
        Coffee coffee = new Coffee();
        model.addAttribute("product", coffee);
        return "/Admin/Menu/coffee-card";
    }
    @GetMapping("coffee-delete/{id}")
    public String delleteCoffee(@PathVariable("id") Long id) {
        Coffee obj = coffeeService.findById(id);
        try {
            Files.deleteIfExists(Path.of(uploadPath + obj.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        coffeeService.deleteById(id);
        return "redirect:/coffee-list";
    }

    @PostMapping("/coffee-card-save")
    public String saveCoffee(@RequestParam(name = "id", defaultValue = "0") Long id, @RequestParam("name") String name, @RequestParam("description") String description,
                             @RequestParam("sValue") String sValue, @RequestParam("sPrice") Double sPrice, @RequestParam("mValue") String mValue, @RequestParam("mPrice") Double mPrice,
                             @RequestParam("lValue") String lValue, @RequestParam("lPrice") Double lPrice,@RequestParam("xlValue") String xlValue, @RequestParam("xlPrice") Double xlPrice,
                             @RequestParam("image") MultipartFile file) throws IOException {
        Coffee coffee = new Coffee();
        Coffee oldCoffee = new Coffee();
        if (id>0) { coffee.setId(id);
            oldCoffee = coffeeService.findById(id);}
        coffee.setName(name);
        coffee.setDescription(description);
        coffee.setSValue(sValue);
        coffee.setSPrice(sPrice);
        coffee.setMValue(mValue);
        coffee.setMPrice(mPrice);
        coffee.setLValue(lValue);
        coffee.setLPrice(lPrice);
        coffee.setXlValue(xlValue);
        coffee.setXlPrice(xlPrice);
        //            сохранение  фото
        if (!(Files.exists(Path.of(uploadPath + "/img/coffee/"))))
            Files.createDirectories(Path.of(uploadPath + "/img/coffee/"));

        if (file.getSize() > 0) {
            if (id>0) Files.deleteIfExists(Paths.get(uploadPath + oldCoffee.getImage()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/img/coffee/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            coffee.setImage("/img/coffee/" + FileNameUuid);
        } else {
            if (oldCoffee.getImage() != null)
                coffee.setImage(oldCoffee.getImage());
        }

        coffeeService.save(coffee);

        return "redirect:/coffee-list";
    }

    //Tea

    @GetMapping("tea-list")
    public String getTeaList(Model model) {
        List<Tea>productList = teaService.findAll();
            model.addAttribute("product", productList);
        return "/Admin/Menu/tea-list";
    }
    @GetMapping("tea-update/{id}")
    public String teaEdit(@PathVariable("id") Long id, Model model) {
        Tea tea = teaService.findById(id);
        model.addAttribute("product", tea);
        return "/Admin/Menu/tea-card";
    }
    @GetMapping("tea-create")
    public String newTeaCreate(Model model) {
        Tea tea = new Tea();
        model.addAttribute("product", tea);
        return "/Admin/Menu/tea-card";
    }
    @GetMapping("tea-delete/{id}")
    public String delleteTea(@PathVariable("id") Long id) {
        Tea obj = teaService.findById(id);
        try {
            Files.deleteIfExists(Path.of(uploadPath + obj.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       teaService.deleteById(id);
        return "redirect:/tea-list";
    }

    @PostMapping("/tea-card-save")
    public String saveTea(@RequestParam(name = "id", defaultValue = "0") Long id, @RequestParam("name") String name, @RequestParam("description") String description,
                               @RequestParam("value") String value, @RequestParam("price") Double price, @RequestParam("image") MultipartFile file) throws IOException {
        Tea tea = new Tea();
        Tea oldTea = new Tea();
        if (id>0) { tea.setId(id);
            oldTea = teaService.findById(id);}
        tea.setName(name);
        tea.setDescription(description);
        tea.setValue(value);
        tea.setPrice(price);
        //            сохранение  фото
        if (!(Files.exists(Path.of(uploadPath + "/img/tea/"))))
            Files.createDirectories(Path.of(uploadPath + "/img/tea/"));

        if (file.getSize() > 0) {
            if (id>0) Files.deleteIfExists(Paths.get(uploadPath + oldTea.getImage()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/img/tea/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            tea.setImage("/img/tea/" + FileNameUuid);
        } else {
            if (oldTea.getImage() != null)
                tea.setImage(oldTea.getImage());
        }

        teaService.save(tea);

        return "redirect:/tea-list";
    }

//   Snack

    @GetMapping("snack-list")
    public String getSnackList(Model model) {
        List<Snack>productList = snackService.findAll();
        model.addAttribute("product", productList);
        return "/Admin/Menu/snack-list";
    }
    @GetMapping("snack-update/{id}")
    public String snackEdit(@PathVariable("id") Long id, Model model) {
        Snack snack = snackService.findById(id);
        model.addAttribute("product", snack);
        return "/Admin/Menu/snack-card";
    }
    @GetMapping("snack-create")
    public String newSnackCreate(Model model) {
        Snack snack = new Snack();
        model.addAttribute("product", snack);
        return "/Admin/Menu/snack-card";
    }
    @GetMapping("snack-delete/{id}")
    public String delleteSnack(@PathVariable("id") Long id) {
        Snack obj = snackService.findById(id);
        try {
            Files.deleteIfExists(Path.of(uploadPath + obj.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        snackService.deleteById(id);
        return "redirect:/snack-list";
    }

    @PostMapping("/snack-card-save")
    public String saveSnack(@RequestParam(name = "id", defaultValue = "0") Long id, @RequestParam("name") String name, @RequestParam("description") String description,
                               @RequestParam("value") String value, @RequestParam("price") Double price, @RequestParam("image") MultipartFile file) throws IOException {
        Snack snack = new Snack();
        Snack oldSnack = new Snack();
        if (id>0) { snack.setId(id);
            oldSnack = snackService.findById(id);}
        snack.setName(name);
        snack.setDescription(description);
        snack.setValue(value);
        snack.setPrice(price);
        //            сохранение  фото
        if (!(Files.exists(Path.of(uploadPath + "/img/snack/"))))
            Files.createDirectories(Path.of(uploadPath + "/img/snack/"));

        if (file.getSize() > 0) {
            if (id>0) Files.deleteIfExists(Paths.get(uploadPath + oldSnack.getImage()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/img/snack/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            snack.setImage("/img/snack/" + FileNameUuid);
        } else {
            if (oldSnack.getImage() != null)
                snack.setImage(oldSnack.getImage());
        }

        snackService.save(snack);

        return "redirect:/snack-list";
    }

//   dessert

    @GetMapping("dessert-list")
    public String getDessertList(Model model) {
        List<Dessert>productList = dessertService.findAll();
        model.addAttribute("product", productList);
        return "/Admin/Menu/dessert-list";
    }
    @GetMapping("dessert-update/{id}")
    public String dessertEdit(@PathVariable("id") Long id, Model model) {
        Dessert dessert = dessertService.findById(id);
        model.addAttribute("product", dessert);
        return "/Admin/Menu/dessert-card";
    }
    @GetMapping("dessert-create")
    public String newDessertCreate(Model model) {
        Dessert dessert = new Dessert();
        model.addAttribute("product", dessert);
        return "/Admin/Menu/dessert-card";
    }
    @GetMapping("dessert-delete/{id}")
    public String delleteDessert(@PathVariable("id") Long id) {
        Dessert obj = dessertService.findById(id);
        try {
            Files.deleteIfExists(Path.of(uploadPath + obj.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dessertService.deleteById(id);
        return "redirect:/dessert-list";
    }

    @PostMapping("/dessert-card-save")
    public String saveDessert(@RequestParam(name = "id", defaultValue = "0") Long id, @RequestParam("name") String name, @RequestParam("description") String description,
                            @RequestParam("value") String value, @RequestParam("price") Double price, @RequestParam("image") MultipartFile file) throws IOException {
        Dessert dessert = new Dessert();
        Dessert oldDessert = new Dessert();
        if (id>0) { dessert.setId(id);
            oldDessert = dessertService.findById(id);}
        dessert.setName(name);
        dessert.setDescription(description);
        dessert.setValue(value);
        dessert.setPrice(price);
        //            сохранение  фото
        if (!(Files.exists(Path.of(uploadPath + "/img/dessert/"))))
            Files.createDirectories(Path.of(uploadPath + "/img/dessert/"));

        if (file.getSize() > 0) {
            if (id>0) Files.deleteIfExists(Paths.get(uploadPath + oldDessert.getImage()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/img/dessert/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            dessert.setImage("/img/dessert/" + FileNameUuid);
        } else {
            if (oldDessert.getImage() != null)
                dessert.setImage(oldDessert.getImage());
        }

        dessertService.save(dessert);

        return "redirect:/dessert-list";
    }

    //   sandwich

    @GetMapping("sandwich-list")
    public String getSandwichList(Model model) {
        List<Sandwich>productList = sandwichService.findAll();
        model.addAttribute("product", productList);
        return "/Admin/Menu/sandwich-list";
    }
    @GetMapping("sandwich-update/{id}")
    public String sandwichEdit(@PathVariable("id") Long id, Model model) {
        Sandwich sandwich = sandwichService.findById(id);
        model.addAttribute("product", sandwich);
        return "/Admin/Menu/sandwich-card";
    }
    @GetMapping("sandwich-create")
    public String newSandwichCreate(Model model) {
        Sandwich sandwich = new Sandwich();
        model.addAttribute("product", sandwich);
        return "/Admin/Menu/sandwich-card";
    }
    @GetMapping("sandwich-delete/{id}")
    public String delleteSandwich(@PathVariable("id") Long id) {
        Sandwich obj = sandwichService.findById(id);
        try {
            Files.deleteIfExists(Path.of(uploadPath + obj.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sandwichService.deleteById(id);
        return "redirect:/sandwich-list";
    }

    @PostMapping("/sandwich-card-save")
    public String saveSandwich(@RequestParam(name = "id", defaultValue = "0") Long id, @RequestParam("name") String name, @RequestParam("description") String description,
                              @RequestParam("value") String value, @RequestParam("price") Double price, @RequestParam("image") MultipartFile file) throws IOException {
        Sandwich sandwich = new Sandwich();
        Sandwich oldSandwich = new Sandwich();
        if (id>0) { sandwich.setId(id);
            oldSandwich = sandwichService.findById(id);}
        sandwich.setName(name);
        sandwich.setDescription(description);
        sandwich.setValue(value);
        sandwich.setPrice(price);
        //            сохранение  фото
        if (!(Files.exists(Path.of(uploadPath + "/img/sandwich/"))))
            Files.createDirectories(Path.of(uploadPath + "/img/sandwich/"));

        if (file.getSize() > 0) {
            if (id>0) Files.deleteIfExists(Paths.get(uploadPath + oldSandwich.getImage()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/img/sandwich/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sandwich.setImage("/img/sandwich/" + FileNameUuid);
        } else {
            if (oldSandwich.getImage() != null)
                sandwich.setImage(oldSandwich.getImage());
        }

        sandwichService.save(sandwich);

        return "redirect:/sandwich-list";
    }


    @GetMapping("admin-menu-additives/{id}")
    public String additivesList(@PathVariable("id") String id, Model model) {
        System.out.println(id);
        List<CoffeeAdditive> coffeeAdditiveList = coffeeAdditivesService.findAll();
        if (id.equalsIgnoreCase("SYRUPS")) {
            List<CoffeeAdditive>productList = coffeeAdditiveList.stream().
                            filter(coffeeAdditive -> coffeeAdditive.getType().
                            equals(Type.SYRUPS)).collect(Collectors.toList());
            System.out.println(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("type", "SYRUPS");
        } else if (id.equalsIgnoreCase("ALCOHOL")) {
            List<CoffeeAdditive>productList = coffeeAdditiveList.stream().
                    filter(coffeeAdditive -> coffeeAdditive.getType().
                            equals(Type.ALCOHOL)).collect(Collectors.toList());
            System.out.println(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("type", "ALCOHOL");
        } else if (id.equalsIgnoreCase("MILK")) {
            List<CoffeeAdditive>productList = coffeeAdditiveList.stream().
                    filter(coffeeAdditive -> coffeeAdditive.getType().
                            equals(Type.MILK)).collect(Collectors.toList());
            System.out.println(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("type", "MILK");
        } else if (id.equalsIgnoreCase("ADD")) {
            List<CoffeeAdditive>productList = coffeeAdditiveList.stream().
                    filter(coffeeAdditive -> coffeeAdditive.getType().
                            equals(Type.ADD)).collect(Collectors.toList());
            System.out.println(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("type", "ADD");
        }
        return "/Admin/Menu/additives-list";
    }

}
