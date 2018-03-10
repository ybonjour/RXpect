# RXpect ![Build Status](https://travis-ci.org/ybonjour/RXpect.svg?branch=master)
Unit Testing RX Applications

### Test subscribe
```kotlin
class MyPresenter(private val view: MyView) {
    fun onResume() {
        disposable = users.getUsers().subscribeBy {
            onSuccess = ::println
            onError = { println("ERROR: $it") }
        }
    }
}
```

```kotlin
@Test
fun showsUser() {
    val expectation = expectSubscribe(whenever(users.getUsers()).thenEmit(user))
    
    presenter.onResume()
        
    expectation.verify()
    
}
```

### Test dispose
```kotlin
class MyPresenter(private val users: Users) {
    private var disposable: Disposable? = null
    
    fun onResume() {
        disposable = users.getUsers().subscribeBy {
            onSuccess = ::println
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
fun loadingUsersDisposedOnPause() {
    val expectation = expectDispose(users.getUsers()) 
    
    presenter.onResume()
    presenter.onPause()
    
    expectation.verify()
}
```