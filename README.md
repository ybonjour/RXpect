# RXpect ![Build Status](https://travis-ci.org/ybonjour/RXpect.svg?branch=master)
Unit Testing RX Applications

### Test dispose
```kotlin
class MyPresenter(private val users: Users) {
    private var disposable: Disposable? = null
    
    fun onResume() {
        disposable = users.getUsers().subscribeBy {
            onSuccess = { println(it) }
            onError = { println("ERROR: $it") }
        }
    }
    
    fun onPause() {
        disposable?.dispose()
    }
}
```

```kotlin
@Test
fun usersIsDisposedOnPause() {
    val users:Users = mock()
    val presenter = MyPresenter(users)
    val expectation = expectDispose(users.getUsers()) 
    
    presenter.onResume()
    presenter.onPause()
    
    expectation.verify()
}
```