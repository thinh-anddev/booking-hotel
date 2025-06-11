package com.example.booking_hotel.presentation.admin.user
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.booking_hotel.domain.model.User
import com.example.booking_hotel.helper.Constant
import com.example.booking_hotel.presentation.admin.AdminViewModel

@Composable
fun UserDetailScreen(
    user: User,
    navController: NavController,
    viewModel: AdminViewModel= hiltViewModel(),
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf(user.email) }
    var password by remember { mutableStateOf(user.password) }
    var age by remember { mutableStateOf(user.age.toString()) }
    var avatarUrl by remember { mutableStateOf(user.avatar) }
    val context = LocalContext.current
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        viewModel.onSelectedAvatar(uri!!.toString())
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Cập nhật thông tin người dùng", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        AsyncImage(
            model = "${Constant.BASE_URL}$avatarUrl",
            contentDescription = "Avatar",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
                .clip(CircleShape)
                .clickable {
                    imagePickerLauncher.launch("image/*")
                },
            contentScale = ContentScale.Crop
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mật khẩu") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Tuổi") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val updatedUser = user.copy(
                    email = email,
                    password = password,
                    age = age.toIntOrNull() ?: user.age,
                    avatar = avatarUrl
                )
                viewModel.updateUser(navController,context,updatedUser)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Lưu thay đổi")
        }
    }
}
