package com.example.internetshop.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.internetshop.RxAndroidSchedulerRule
import com.example.internetshop.domain.data.model.product.Product
import com.example.internetshop.domain.data.repository.ProductLocalRepository
import com.example.internetshop.domain.data.repository.ProductRemoteRepository
import com.example.internetshop.domain.data.usecase.AddProductToBagUseCase
import com.example.internetshop.domain.data.usecase.GetProductColorsUseCase
import com.example.internetshop.domain.data.usecase.GetProductSizesUseCase
import com.example.internetshop.model.data.adapterStates.BaseSpinnerState
import com.example.internetshop.presentation.viewModel.ProductDetailsViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock


class PdpViewModelTest {
    @RelaxedMockK
    lateinit var productRemoteRepository: ProductRemoteRepository

    @RelaxedMockK
    lateinit var productLocalRepository: ProductLocalRepository

    @RelaxedMockK
    lateinit var getProductSizesUseCase: GetProductSizesUseCase

    @RelaxedMockK
    lateinit var getProductColorsUseCase: GetProductColorsUseCase

    @RelaxedMockK
    lateinit var addProductToBagUseCase: AddProductToBagUseCase

    @RelaxedMockK
    lateinit var sizesSpinnerState: BaseSpinnerState

    @RelaxedMockK
    lateinit var colorsSpinnerState: BaseSpinnerState

    @Rule
    @JvmField
    val androidTestRule = RxAndroidSchedulerRule() // LiveData

    @get:Rule
    val executorRule = InstantTaskExecutorRule() // LiveData

    @Mock
    lateinit var observer: Observer<Product>

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }


    private fun createViewModel(): ProductDetailsViewModel {
        return ProductDetailsViewModel(
            productRemoteRepository,
            productLocalRepository,
            getProductSizesUseCase,
            getProductColorsUseCase,
            addProductToBagUseCase,
            sizesSpinnerState,
            colorsSpinnerState
        )
    }

    @Test
    fun `GIVEN favorite item WHEN favoriteClicked THEN deleteFromFavorite is executed`() {
        val viewModel = createViewModel()
        viewModel.favoriteIsChecked.value = true
        viewModel.product.value = mockk()
        viewModel.favoriteClicked()
        verify(exactly = 1) {
            productLocalRepository.deleteFromFavorite(any())
        }
    }

    @Test
    fun `GIVEN favorite item WHEN favoriteClicked THEN deleting product not found`() {
        //GIVEN
        val viewModel = createViewModel()
        viewModel.favoriteIsChecked.value = true
        viewModel.product.value = null

        // WHEN
        viewModel.favoriteClicked()

        //THEN
        assert(viewModel.event.value is ProductDetailsViewModel.Event.ProductNotFound)
    }

    @Test
    fun `GIVEN not favorite item WHEN favoriteClicked THEN addToFavorite is executed`() {
        // GIVEN
        val viewModel = createViewModel()
        viewModel.favoriteIsChecked.value = false
        viewModel.product.value = mockk()

        // WHEN
        viewModel.favoriteClicked()

        // THEN
        verify(exactly = 1) {
            productLocalRepository.addToFavorite(any())
        }
    }

    @Test
    fun `GIVEN viewModel WHEN getProduct THEN product on display`() {
        // GIVEN
        val productId = "1"
        every {
            productRemoteRepository.getProduct(productId)
        }.returns(Single.just(mockk()))
        val viewModel = createViewModel()

        // WHEN
        viewModel.getProduct(productId)

        // THEN
        assertNotNull(viewModel.product.value)
    }

    @Test
    fun `GIVEN viewModel WHEN getProduct THEN receive throwable`() {
        // GIVEN
        val productId = "1"
        every {
            productRemoteRepository.getProduct(productId)
        }.returns(Single.error(Throwable()))
        val viewModel = createViewModel()

        // WHEN
        viewModel.getProduct(productId)

        // THEN
        assertTrue(viewModel.event.value is ProductDetailsViewModel.Event.ReceiveThrowable)
    }


    @Test
    fun `GIVEN viewModel WHEN isInDB THEN favoriteIsChecked = true`() {
        // GIVEN
        val productId = "1"
        every {
            productRemoteRepository.getProduct(productId)
        }.returns(Single.just(mockk()))
        every {
            productLocalRepository.isProductInDB(any())
        }.returns(Single.just(true))
        val viewModel = createViewModel()

        // WHEN
        viewModel.getProduct(productId)

        // THEN
        assertTrue(viewModel.favoriteIsChecked.value == true)
    }
//    @Test
//    fun `GIVEN viewModel WHEN isInDB THEN favoriteIsChecked = false`() {
//        // GIVEN
//        val productId = "1"
//        every {
//            productRemoteRepository.getProduct(productId)
//        }.returns(Single.just(mockk()))
//        every {
//            productLocalRepository.isProductInDB(productId)
//        }.returns(Single.just(false))
//        val viewModel = createViewModel()
//
//        // WHEN
//        viewModel.getProduct(productId)
//
//        // THEN
//        assertNotNull(viewModel.favoriteIsChecked.value == true)
//    }

    @Test
    fun `GIVEN emptyProduct WHEN addToFavorite THEN ProductNotFound is sent`() {
        // GIVEN
        val viewModel = createViewModel()
        viewModel.product.value = null

        // WHEN
        viewModel.addToFavorite()

        // THEN
        assertTrue(
            viewModel.event.value
                    is ProductDetailsViewModel.Event.ProductNotFound
        )
    }
}