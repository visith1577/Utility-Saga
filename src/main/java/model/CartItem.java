package model;

public class CartItem {
    private Integer id;
    private Integer itemId;
    private Integer userSessionId;
    private Integer quantity;
    private Double totalPrice; // itemPrice * quantity
    private String timestamp;
    private Integer availabilityStatus; // default 1

    public enum CartItemAvailabilityStatus {
        AVAILABLE(1), DELETED(0);
        private final Integer key;
        CartItemAvailabilityStatus(Integer key) {
            this.key = key;
        }
        public Integer getKey() {
            return key;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(Integer userSessionId) {
        this.userSessionId = userSessionId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public CartItemAvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus == 1 ? CartItemAvailabilityStatus.AVAILABLE : CartItemAvailabilityStatus.DELETED;
    }

    public void setAvailabilityStatus(CartItemAvailabilityStatus deleteStatus) {
        this.availabilityStatus = deleteStatus.getKey();
    }
}
