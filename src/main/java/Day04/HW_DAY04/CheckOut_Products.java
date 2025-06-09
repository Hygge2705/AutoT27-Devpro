package Day04.HW_DAY04;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class CheckOut_Products {
    public static void main(String[] args) {
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

//  Step1 : Login vào https://www.saucedemo.com/
    //    Truy cập trang web
        driver.get("https://www.saucedemo.com/");
        System.out.println("Title of the Web: " +driver.getTitle());
    //    Nhập thông tin và dăng nhập
        WebElement inputUsername = driver.findElement(By.id("user-name"));
        inputUsername.sendKeys("standard_user");

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");

        WebElement buttonLogin = driver.findElement(By.id("login-button"));
        buttonLogin.click();

        System.out.println("Step 1 : Login vào https://www.saucedemo.com/ successfully!");

//    Step 2: Chọn tìm kiếm droplist Price (low to high)
        WebElement sortBy = driver.findElement(By.className("product_sort_container"));
        Select optionOfSortBy = new Select(sortBy);
        optionOfSortBy.selectByVisibleText("Price (low to high)");
        System.out.println("Step 2: Chọn tìm kiếm droplist Price (low to high) successfully!");

//    Step 3: Add to cart 2 sản phẩm bất kì
//        WebElement buttonAddToCartPrd01 = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
//        buttonAddToCartPrd01.click();
//        WebElement buttonAddToCartPrd02 = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
//        buttonAddToCartPrd02.click();

        WebElement product01 = driver.findElement(By.xpath("(//div[@class='inventory_item'])[1]"));
        String product01Name = product01.findElement(By.className("inventory_item_name")).getText();
        String product01Price = product01.findElement(By.className("inventory_item_price")).getText();
        product01.findElement(By.tagName("button")).click();

        WebElement product02 = driver.findElement(By.xpath("(//div[@class='inventory_item'])[2]"));
        String product02Name = product02.findElement(By.className("inventory_item_name")).getText();
        String product02Price = product02.findElement(By.className("inventory_item_price")).getText();
        product02.findElement(By.tagName("button")).click();

        System.out.println("Step 3: Add to cart 2 sản phẩm bất kì successfully!");
        System.out.println("\tTên sản phẩm 1: " + product01Name + ". ");
        System.out.println("\tGiá sản phẩm 1: " + product01Price);
        System.out.println("\tTên sản phẩm 2: " + product02Name + ". ");
        System.out.println("\tGiá sản phẩm 2: " + product02Price);

    //    Kiểm tra đúng Expect: ở giỏ hàng hiển thị số 2?
        WebElement cartCount = driver.findElement(By.className("shopping_cart_badge"));
        String cartNumber = cartCount.getText();

        if (cartNumber.equals("2")){
            System.out.println("\tTrue. Number of product is 2");
        }else {
            System.out.println("\tFalse. Number of product is "+cartNumber);
        }

//    Step 4: Click vào giỏ hàng
        WebElement buttonShoppingCart = driver.findElement(By.className("shopping_cart_link"));
        buttonShoppingCart.click();
        System.out.println("Step 4: Open shopping Cart successfully!");

    //    Expect 1: Thông tin Your Cart hiển thị đúng 2 sản phẩm với tên và giá tiền đúng
        WebElement productCart01 = driver.findElement(By.xpath("(//div[@class='cart_item'])[1]"));
        String productCart01Name = productCart01.findElement(By.className("inventory_item_name")).getText();
        String productCart01Price = productCart01.findElement(By.className("inventory_item_price")).getText();
        String productCart01Qty = productCart01.findElement(By.className("cart_quantity")).getText();

        WebElement productCart02 = driver.findElement(By.xpath("(//div[@class='cart_item'])[2]"));
        String productCart02Name = productCart02.findElement(By.className("inventory_item_name")).getText();
        String productCart02Price = productCart02.findElement(By.className("inventory_item_price")).getText();
        String productCart02Qty = productCart02.findElement(By.className("cart_quantity")).getText();

        if (productCart01Name.equals(product01Name) && productCart01Price.equals(product01Price)){
            System.out.println("\tProduct 1 in the cart matches the previously selected product.");
        }else {
            System.out.println("\tProduct 1 in the cart doesn't the previously selected product.");
        }
        if (productCart02Name.equals(product02Name) && productCart02Price.equals(product02Price)){
            System.out.println("\tProduct 1 in the cart matches the previously selected product.");
        }else {
            System.out.println("\tProduct 1 in the cart doesn't the previously selected product.");
        }

    //    Expect 2: Màn hình có hiển thị 2 button Remove
        if(driver.findElements(By.className("cart_button")).size()==2){
            System.out.println("\tHave 2 removeButtons. Case is passed.");
        }else {
            System.out.println("\tHave " + driver.findElements(By.className("cart_button")).size() + " removeButtons. Case is failed.");
        }

//    Step 5: Click continue và nhập các thông tin First Name, Last name, Zip code
        WebElement checkoutButton =  driver.findElement(By.id("checkout"));
        checkoutButton.click();
        WebElement inputFirstName =  driver.findElement(By.id("first-name"));
        inputFirstName.sendKeys("Thom");
        WebElement inputLastName =  driver.findElement(By.id("last-name"));
        inputLastName.sendKeys("Nguyen");
        WebElement inputPostalCode =  driver.findElement(By.id("postal-code"));
        inputPostalCode.sendKeys("10020");
        System.out.println("Step 5: Click checkout và Nhập thông tin checkout thành công.");

//    Step 6: Click continue
        WebElement continueButton =  driver.findElement(By.id("continue"));
        continueButton.click();
        System.out.println("Step 6: Click continue thành công.");

        //    Expect 1 : Thông tin Description hiển thị đúng 2 sản phẩm với số lượng, tên và giá tiền đúng
        WebElement productCheckout01 = driver.findElement(By.xpath("(//div[@class='cart_item'])[1]"));
        String productCheckout01Name = productCheckout01.findElement(By.className("inventory_item_name")).getText();
        String productCheckout01Price = productCheckout01.findElement(By.className("inventory_item_price")).getText();
        String productCheckout01Qty = productCheckout01.findElement(By.className("cart_quantity")).getText();

        WebElement productCheckout02 = driver.findElement(By.xpath("(//div[@class='cart_item'])[2]"));
        String productCheckout02Name = productCheckout02.findElement(By.className("inventory_item_name")).getText();
        String productCheckout02Price = productCheckout02.findElement(By.className("inventory_item_price")).getText();
        String productCheckout02Qty = productCheckout01.findElement(By.className("cart_quantity")).getText();

        //check số lượng loại sản phẩm được thanh toán
        if(driver.findElements(By.className("cart_item")).size()==2){
            System.out.println("\tHave 2 products. Case is passed.");
        }else {
            System.out.println("\tHave " + driver.findElements(By.className("cart_item")).size() + " products. Case is failed.");
        }
        //check tên, giá và số lượng mỗi loại
        if (productCheckout01Name.equals(productCart01Name) && productCheckout01Price.equals(productCart01Price) && productCheckout01Qty.equals(productCart01Qty)){
            System.out.println("\tProduct 1 in the bill matches the previously selected product.");
        }else {
            System.out.println("\tProduct 1 in the bill doesn't the previously selected product.");
        }
        if (productCheckout02Name.equals(productCart02Name) && productCheckout02Price.equals(productCart02Price) && productCheckout02Qty.equals(productCart02Qty)){
            System.out.println("\tProduct 2 in the bill matches the previously selected product.");
        }else {
            System.out.println("\tProduct 2 in the bill doesn't the previously selected product.");
        }

    //    Expect 2: Shipping Information hiển thị đúng "Free Pony Express Delivery!"
        if (driver.findElement(By.xpath("//div[@class='summary_info']//div[@data-test='shipping-info-value']")).getText().equals("Free Pony Express Delivery!")){
            System.out.println("\tShipping Information hiển thị đúng: 'Free Pony Express Delivery!'");
        }else {
            System.out.println("\tShipping Information hiển thị sai. '"+driver.findElement(By.xpath("//div[@class='summary_info']//div[@data-test='shipping-info-value']")).getText() +"'.");
        }

    //    Expect 3: Price Total hiển thị đúng tổng tiền 2 sản phẩm
        WebElement priceTotal = driver.findElement(By.xpath("//div[@class='summary_info']//div[@data-test='subtotal-label']"));
        String itemTotalText = priceTotal.getText();
        double displayedTotal = Double.parseDouble(itemTotalText.replace("Item total: $", ""));

    // Chuyển dữ liệu từ String sang số và tính tổng
        int qty1 = Integer.parseInt(productCheckout01Qty);
        int qty2 = Integer.parseInt(productCheckout02Qty);

        double price1 = Double.parseDouble(productCheckout01Price.replace("$", ""));
        double price2 = Double.parseDouble(productCheckout02Price.replace("$", ""));

        double expectedTotal = qty1 * price1 + qty2 * price2;

        if (displayedTotal == expectedTotal) {
            System.out.println("\tPrice total is true. " + displayedTotal);
        } else {
            System.out.println("\tPrice total is false. " + displayedTotal);
        }

    //    Expect 4: Total hiển thị đúng tổng tiền của Item total + Tax

        if (displayedTotal == expectedTotal) {
            WebElement total = driver.findElement(By.xpath("//div[@class='summary_info']//div[@data-test='total-label']"));
            String totalText = total.getText();
            double displayTotal = Double.parseDouble(totalText.replace("Total: $", ""));

            WebElement tax = driver.findElement(By.xpath("//div[@class='summary_info']//div[@data-test='tax-label']"));
            String taxText = tax.getText();
            double displayTax = Double.parseDouble(taxText.replace("Tax: $", ""));

            if (displayTotal == expectedTotal+displayTax) {
                System.out.println("\tTotal is true. " + displayTotal);
            } else {
                System.out.println("\tTotal is false. " + displayTotal);
            }
        } else {
            System.out.println("\tPrice total is false. " + displayedTotal);
        }

    //    Expect 5: Button Finish hiển thị
        WebElement finishButton = driver.findElement(By.id("finish"));
        if (finishButton.isDisplayed()){
            System.out.println("\tFinish button is displayed ");
        }else {
            System.out.println("\tFinish button isn't displayed ");
        }

//    Step 7: Click Finish
        finishButton.click();
        System.out.println("Step 7: Click Finish thành công.");

        //    Expect 1 : hiển thị "Checkout: Complete!"
        WebElement title = driver.findElement(By.className("title"));
        if (title.getText().equals("Checkout: Complete!")){
            System.out.println("\tTittle is true: " + title.getText());
        }else {
            System.out.println("\tTittle is false: " + title.getText());
        }

    //    Expect 2 : hiển thị "Thank you for your order!"
        WebElement header = driver.findElement(By.className("complete-header"));
        if (header.getText().equals("Thank you for your order!")){
            System.out.println("\tHeader is true: " + header.getText());
        }else {
            System.out.println("\tHeader is false: " + header.getText());
        }

    //    Expect 3 : hiển thị "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
        WebElement text = driver.findElement(By.className("complete-text"));
        if (text.getText().equals("Your order has been dispatched, and will arrive just as fast as the pony can get there!")){
            System.out.println("\ttext is true: " + text.getText());
        }else {
            System.out.println("\ttext is false: " + text.getText());
        }
    //    Expect 3 : hiển thị button Back Home
        WebElement backHomeButton = driver.findElement(By.id("back-to-products"));
        if (backHomeButton.isDisplayed()){
            System.out.println("\tbackHomeButton is displayed ");
        }else {
            System.out.println("\tbackHomeButton isn't displayed ");
        }

//    Thoát trình duyệt sau khi hoàn thành
        driver.quit();
    }
}
