
# **Tamara Android SDK Documentation**

The Tamara Android SDK enables seamless integration of Tamara's payment solutions into your Android application. This document provides detailed instructions for setting up the SDK, creating and managing orders, and rendering widgets.

---

## **Table of Contents**

1. [SDK Requirements](#sdk-requirements)  
2. [Initialize the SDK](#initialize-the-sdk)  
3. [Create and Pay Orders](#create-and-pay-orders)  
4. [Order Details](#order-details)  
5. [Authorize Orders](#authorize-orders)  
6. [Cancel Orders](#cancel-orders)  
7. [Update Order Reference](#update-order-reference)  
8. [Capture Payments](#capture-payments)  
9. [Refunds](#refunds)  
10. [Render Widgets](#render-widgets)  
11. [Handle Activity Results](#handle-activity-results)

---

## **1. SDK Requirements**

To integrate the Tamara SDK into your app, you need the following:

- **Authentication Token (`AUTH_TOKEN`)**: Unique token for API authentication.
- **API URL (`API_URL`)**: Endpoint for API requests.
- **Notification Webhook URL (`NOTIFICATION_WEB_HOOK_URL`)**: Server endpoint for receiving notifications.
- **Public Key (`PUBLIC_KEY`)**: Used for rendering widgets.
- **Notification Token (`NOTIFICATION_TOKEN`)**: Secure token for notifications.
- **Environment Configuration (`isSandbox`)**:  
  - `true`: Sandbox account.  
  - `false`: Production account.

### Example:
```kotlin
const val API_URL = "https://api-sandbox.tamara.co/"
const val AUTH_TOKEN = "your-api-token"
const val NOTIFICATION_WEB_HOOK_URL = "https://your.site/pushnotification"
const val PUBLISH_KEY = "your-public-key"
const val NOTIFICATION_TOKEN = "your-notification-token"
val isSandbox = true // Set to false for production
```

---

## **2. Initialize the SDK**

Before using the SDK, initialize it with the required credentials:

```kotlin
TamaraPayment.initialize(
    AUTH_TOKEN, 
    API_URL, 
    NOTIFICATION_WEB_HOOK_URL, 
    PUBLISH_KEY, 
    NOTIFICATION_TOKEN, 
    isSandbox
)
```

---

## **3. Create and Pay Orders**

If you’re using your backend to create an order and only utilizing our SDK to redirect to the Tamara checkout page, please use the following function:
```kotlin
TamaraPayment.startPayment(activity, checkoutURL = "tamara_checkout_url", successCallBackURL = "tamara://success", failureCallBackURL = "tamara://failure", cancelCallbackURL = "tamara://cancel")
```

### **Example: Complete Order Creation Process**

```kotlin
// Initialize Order
TamaraPayment.createOrder("Order Description")

// Set Customer Information
TamaraPayment.setCustomerInfo(
    firstName = "John",
    lastName = "Doe",
    phoneNumber = "123456789",
    email = "john.doe@example.com",
    isFirstOrder = true
)

// Add Items
TamaraPayment.addItem(
    name = "Laptop",
    referenceId = "item_12345",
    sku = "sku_98765",
    type = "physical",
    unitPrice = 500.0,
    taxAmount = 25.0,
    discountAmount = 50.0,
    quantity = 1
)

// Set Shipping Address
TamaraPayment.setShippingAddress(
    firstName = "John",
    lastName = "Doe",
    phone = "123456789",
    addressLine1 = "123 Main St",
    addressLine2 = "Apt 4B",
    country = "SA",
    region = "Riyadh",
    city = "Riyadh"
)

// Set Payment Type (Optional)
TamaraPayment.setPaymentType("PAY_BY_INSTALMENTS")

// Apply Shipping Fee and Discount
TamaraPayment.setShippingAmount(20.0)
TamaraPayment.setDiscount(15.0, "Promotional Discount")

// Set Additional Fields
TamaraPayment.setOrderNumber("ORDER12345")
TamaraPayment.setOrderReferenceId("REF12345")
TamaraPayment.setLocale("en-US")

//Set Some Other Information (Optional)
TamaraPayment.setPlatform("Tamara-Android-SDK")
TamaraPayment.setRiskAssessment(jsonData, activity)
TamaraPayment.setAdditionalData(jsonData)


// Start Payment
TamaraPayment.startPayment(activity)
```

---

## **4. Order Details**

Retrieve detailed information about an order using the order ID:
```kotlin
TamaraPayment.getOrderDetail(this, orderId = "ORDER_ID")
```

**Example Response:**
```json
{
  "orderId": "9af217f1-9e4a-400e-9e06-2b7f4f40687e",
  "orderReferenceId": "REF12345",
  "orderNumber": "ORDER12345",
  "status": "approved",
  "totalAmount": { "amount": 520.0, "currency": "SAR" },
  "items": [
    {
      "name": "Laptop",
      "quantity": 1,
      "unitPrice": { "amount": 500.0, "currency": "SAR" },
      "taxAmount": { "amount": 25.0, "currency": "SAR" }
    }
  ]
}
```

---

## **5. Authorize Orders**

After receiving a notification about an approved order:
```kotlin
TamaraPayment.authoriseOrder(this, orderId = "ORDER_ID")
```

---

## **6. Cancel Orders**

Cancel an order before shipment:
```kotlin
TamaraPayment.cancelOrder(this, orderId = "ORDER_ID", jsonData = json)
```

---

## **7. Update Order Reference**

Update the order ID after it is authorized:
```kotlin
TamaraPayment.updateOrderReference(this, orderId = "ORDER_ID", orderReference = "NEW_REF_ID")
```

---

## **8. Capture Payments**

Capture a payment after shipping the order:
```kotlin
TamaraPayment.getCapturePayment(this, jsonData = json)
```

---

## **9. Refund**

Issue a refund for an authorized order:
```kotlin
TamaraPayment.refunds(this, orderId = "ORDER_ID", jsonData = json)
```

---

## **10. Render Tamara Widget**

### **Render Product Widget**
```kotlin
TamaraPayment.renderWidgetProduct(
    this, language = "en", country = "SA", publicKey = "your-public-key", amount = 250.0
)
```

### **Render Checkout Widget**
```kotlin
TamaraPayment.renderWidgetCartPage(
    this, language = "en", country = "SA", publicKey = "your-public-key", amount = 250.0
)
```

---

## **11. Handle Activity Results**

Handle results from Tamara SDK activities:
```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (TamaraPaymentHelper.shouldHandleActivityResult(requestCode, resultCode, data)) {
        val result = TamaraPaymentHelper.getData(data!!)
        when (result?.status) {
            PaymentResult.STATUS_CANCEL -> {
              //Payment has been cancelled 
            }
            PaymentResult.STATUS_FAILURE -> {
              //Payment has occurred an error
            }
            PaymentResult.STATUS_SUCCESS -> {
              //Payment has been made successfully
            }
        }
    }
}
```

---

## Support

For questions or issues, contact **Tamara Integration Support** at integrations@tamara.co.