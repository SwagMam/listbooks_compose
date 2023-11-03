package com.compose.jetbooks.ui.theme.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.compose.jetbooks.R
import com.compose.jetbooks.di.Injection
import com.compose.jetbooks.ui.theme.JetBooksTheme
import com.compose.jetbooks.ui.theme.common.UiState
import com.compose.jetbooks.ui.theme.screen.ViewModelFactory

@Composable
fun DetailScreen(
    bookId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideBooksRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getBooksById(bookId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    author = data.book.author,
                    logo = data.book.logo,
                    type = data.book.type,
                    description = data.book.desc,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
        }
    }
}


@Composable
fun DetailContent(
    author: String,
    logo: Int,
    type: String,
    description: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = stringResource(R.string.back),
        modifier = Modifier
            .padding(16.dp)
            .clickable { onBackClick() }
    )
    Column(
        modifier = modifier
    ) {
        LogoDetail(logo = logo, modifier = modifier)
        Spacer(modifier = modifier.width(24.dp))
        ContentDescriptionDetail(author, type, description)
    }
}

@Composable
fun LogoDetail(
    logo: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(48.dp, 48.dp, 48.dp, 8.dp)
            .clip(RectangleShape)
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .clip(CircleShape)
        ) {
            AsyncImage(
                modifier = modifier.fillMaxWidth(),
                model = logo,
                alignment = Alignment.Center,
                contentDescription = stringResource(R.string.logo),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun ContentDescriptionDetail(
    author: String,
    type: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier.padding(
                        start = 18.dp,
                        top = 18.dp,
                        end = 18.dp,
                    ),
                    text = author,
                    style = MaterialTheme.typography.displaySmall
                )
            }
            Text(
                modifier = modifier.padding(
                    start = 18.dp,
                    bottom = 12.dp
                ),
                text = type,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = modifier.padding(18.dp),
                text = description,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    DetailContent(
        author = "author",
        logo = 0,
        type = "type",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        onBackClick = {}
    )
}


@Preview(showBackground = true)
@Composable
fun ContentDescriptionPreview() {
    JetBooksTheme() {
        ContentDescriptionDetail(
            "Henry Manampiring",
            "Self Improvement",
            "Books Description",
        )
    }
}