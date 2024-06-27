package gift;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class HttpProductHandler implements HttpHandler {
    private final ProductController productController;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public HttpProductHandler(ProductController productController) {
        this.productController = productController;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String method = exchange.getRequestMethod();
            System.out.println("Received " + method + " request");

            switch (method) {
                case "GET":
                    handleGetRequest(exchange);
                    break;
                case "POST":
                    handlePostRequest(exchange);
                    break;
                case "PUT":
                    handlePutRequest(exchange);
                    break;
                case "DELETE":
                    handleDeleteRequest(exchange);
                    break;
                default:
                    exchange.sendResponseHeaders(405, -1); // Method Not Allowed
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(500, -1); // Internal Server Error
        }
    }

    private void handleGetRequest(HttpExchange exchange) throws IOException {
        List<Product> products = productController.getProducts();
        String responseJson = objectMapper.writeValueAsString(products);

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, responseJson.getBytes().length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseJson.getBytes());
        }
    }

    private void handlePostRequest(HttpExchange exchange) throws IOException {
        Product product = objectMapper.readValue(exchange.getRequestBody(), Product.class);
        System.out.println("Received product: " + product.getName());
        Product addedProduct = productController.addProduct(product);
        String responseJson = objectMapper.writeValueAsString(addedProduct);

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, responseJson.getBytes().length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseJson.getBytes());
        }
    }

    private void handlePutRequest(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String[] pathParts = path.split("/");
        if (pathParts.length != 4) {
            exchange.sendResponseHeaders(400, -1); // Bad Request
            return;
        }

        long id;
        try {
            id = Long.parseLong(pathParts[3]);
        } catch (NumberFormatException e) {
            exchange.sendResponseHeaders(400, -1); // Bad Request
            return;
        }

        Product updatedProductData = objectMapper.readValue(exchange.getRequestBody(), Product.class);
        Product updatedProduct = productController.updateProduct(id, updatedProductData);

        if (updatedProduct == null) {
            exchange.sendResponseHeaders(404, -1); // Not Found
            return;
        }

        String responseJson = objectMapper.writeValueAsString(updatedProduct);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, responseJson.getBytes().length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseJson.getBytes());
        }
    }
    private void handleDeleteRequest(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String[] pathParts = path.split("/");
        if (pathParts.length != 4) {
            exchange.sendResponseHeaders(400, -1); // Bad Request
            return;
        }

        long id;
        try {
            id = Long.parseLong(pathParts[3]);
        } catch (NumberFormatException e) {
            exchange.sendResponseHeaders(400, -1); // Bad Request
            return;
        }

        Product deletedProduct = productController.deleteProduct(id);

        if (deletedProduct == null) {
            exchange.sendResponseHeaders(404, -1); // Not Found
            return;
        }

        String responseJson = objectMapper.writeValueAsString(deletedProduct);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, responseJson.getBytes().length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseJson.getBytes());
        }
    }
}
