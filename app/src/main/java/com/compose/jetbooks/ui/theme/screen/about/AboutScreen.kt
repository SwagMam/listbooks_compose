package com.compose.jetbooks.ui.theme.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.jetbooks.ui.theme.JetBooksTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(com.compose.jetbooks.R.string.back),
            modifier = Modifier
                .padding(16.dp)
                .clickable { navigateBack() }
        )
        Image(
            modifier = modifier
                .fillMaxWidth()
                .height(360.dp)
                .padding(16.dp)
                .clip(CircleShape),
            painter = painterResource(com.compose.jetbooks.R.drawable.profile),
            contentScale = ContentScale.FillWidth,
            contentDescription = stringResource(com.compose.jetbooks.R.string.about_me)
        )
        Spacer(modifier = modifier.height(8.dp))
        AboutItem(
            title = stringResource(com.compose.jetbooks.R.string.name),
            field = stringResource(com.compose.jetbooks.R.string.intro_name)
        )
        AboutItem(
            title = stringResource(com.compose.jetbooks.R.string.email),
            field = stringResource(com.compose.jetbooks.R.string.intro_email)
        )
    }
}

@Composable
fun AboutItem(
    title: String,
    field: String,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(modifier.padding(
            start = 16.dp,
            top = 8.dp,
            end = 8.dp,
            bottom = 8.dp
        )) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                modifier = modifier.padding(bottom = 2.dp),
                text = field,
                style = MaterialTheme.typography.titleMedium
            )
            Divider(color = MaterialTheme.colorScheme.onPrimary, thickness = 2.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutItemReview() {
    JetBooksTheme {
        AboutItem(title = "Test", field = "test@gmail.com")
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    JetBooksTheme {
        AboutScreen(navigateBack = {})
    }
}