# UpSchool x Akbank Android Development Bootcamp Final Project

This project was developed as a graduation project for bootcamp. 

Used technologies:
- Jetpack Compose
- MVVM
- Firebase ( Authentication and Firestore Database)
- Room Database
- Retrofit
- Coroutines
- Hilt
- UI test

## Pages

### Login - Register - Forgot Password
Firebase is used to verify whether you have already registered using the email. The email information is saved to the room database following a successful login.
For the input values, toggle visibility property was used. The "Sign Up" and "Forgot password?" buttons take users to the appropriate pages.

<img src="https://user-images.githubusercontent.com/53402156/182389106-9ce2cb8f-2a40-42dc-bbf7-8dda6b16ff2a.jpg" width=20% height=20%>  <img src="https://user-images.githubusercontent.com/53402156/182389751-0f39eb78-4794-4e72-b887-44dc95799031.jpg" width=20% height=20%>  <img src="https://user-images.githubusercontent.com/53402156/182389761-600c7b36-7abb-444a-a532-c7dd9ca684d4.jpg" width=20% height=20%>  <img src="https://user-images.githubusercontent.com/53402156/182389778-af348504-b0d8-4629-a895-03fbeb0bc826.jpg" width=20% height=20%>


When a registration is successful, the data is stored in the firestore database. If it has already been registered, using "Alredy a member? Login" will take you back to the login screen.

<img src="https://user-images.githubusercontent.com/53402156/182390764-f5c12b93-43ca-467e-ac23-b6067138a308.jpg" width=20% height=20%>  <img src="https://user-images.githubusercontent.com/53402156/182390758-7528aeed-abb7-4b3e-b736-25d206eac81c.jpg" width=20% height=20%>  <img src="https://user-images.githubusercontent.com/53402156/182390751-4807cde6-7adb-48d6-8905-f6a842bcb173.jpg" width=20% height=20%>  

After a valid e-mail address is entered, a password reset e-mail is sent to the e-mail address with the "Send Code" button. Using arrow will take you back to the login screen.

<img src="https://user-images.githubusercontent.com/53402156/182392088-566d4f70-2d14-42bd-95e9-0d69b17631ea.jpg" width=20% height=20%>  <img src="https://user-images.githubusercontent.com/53402156/182392089-be8f0d3f-62fe-4b83-a3e1-e878a36a720a.jpg" width=20% height=20%>  <img src="https://user-images.githubusercontent.com/53402156/182392080-596b19b1-f03e-488d-88cb-75f471d92334.jpg" width=20% height=20%>  

### Main - Detail

The data was gathered using Retorfit. On the detail page, information about the chosen product was shown using this method:
```
@Composable
fun ProductDetailScreen(
    getSelectedProduct: () -> Product?,
) {

    val product = getSelectedProduct()
	
	GlideImage(
		imageModel = product.image,
		contentScale = ContentScale.Crop,
		modifier = Modifier
			.clip(RoundedCornerShape(10.dp))
			.size(200.dp)
	)	
}
```

<img src="https://user-images.githubusercontent.com/53402156/182392717-ce5100fe-eece-4348-8f69-9c930127bd75.jpg" width=20% height=20%>  <img src="https://user-images.githubusercontent.com/53402156/182392720-5dbe1601-aa69-4c66-af18-feab31b4c375.jpg" width=20% height=20%>  <img src="https://user-images.githubusercontent.com/53402156/182392722-87cb0cb9-7555-4e75-97a3-32c476e2058e.jpg" width=20% height=20%>  <img src="https://user-images.githubusercontent.com/53402156/182392702-3d6a87ce-678c-4d15-aff9-49c76361da4f.jpg" width=20% height=20%>  


### Favorite

The product details are stored in the room database and used again during the favorite-adding step. Each time a user logs into the application, their favorite information is removed from the room database.
```
onClick = {
	productDetailViewModel.saveToDb(
		product = product)
}
```
<img src="https://user-images.githubusercontent.com/53402156/182393563-6eaefb59-e708-4a90-8b97-821bd8f4cdc5.jpg" width=20% height=20%>  

### Cart

The request to add products to the cart is sent to the API. The product is deleted, the page is reloaded, and the most recent list is shown when the trash can button is clicked. The items in the basket are removed when the "Checkout" button is clicked.

```
    @POST(END_POINT_GET_PRODUCT_BY_USER)
    @FormUrlEncoded
    suspend fun getProductsByUser(
        @Field("user") user: String,
    ): List<Product>
```
Repository
```
    suspend fun getProductsByUser(): List<Product> {
        return productAPI.getProductsByUser(
            user = "ozlembasabakar"
        )
    }
```

ViewModel 
```
fun addProductToBag(product: Product, quantity: Int) {
	viewModelScope.launch {
		try {
			val result = productRepository.postProducts(product = product, quantity = quantity)
		} catch (exception: Exception) {
			Log.d("Ozlemwasheree", exception.message.toString())
		}
	}
}
```

ProductDetailScreen
```
onClick = {
	productDetailViewModel.addProductToBag(product,
		quantity = count.value)
}
```
<img src="https://user-images.githubusercontent.com/53402156/182393934-9ca8754a-a473-4d67-8b3c-3f2143044f1d.jpg" width=20% height=20%>  <img src="https://user-images.githubusercontent.com/53402156/182393936-815da436-3324-414c-9b07-257f5fb50d08.jpg" width=20% height=20%>  <img src="https://user-images.githubusercontent.com/53402156/182393930-ff50aecb-35fc-48d2-b023-09045cd769ac.jpg" width=20% height=20%>  <img src="https://user-images.githubusercontent.com/53402156/182393940-62731648-103b-43e4-8862-c2371caefd40.jpg" width=20% height=20%>  

















