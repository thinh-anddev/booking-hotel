package com.example.booking_hotel.presentation.admin.user

import android.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.booking_hotel.domain.model.User
import com.example.booking_hotel.helper.Constant
import com.example.booking_hotel.ui.theme.Color_986601

@Composable
fun UserCard(user: User,onClick:(User)->Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp).clickable{onClick.invoke(user)}
        ) {
            AsyncImage(
                model = "${Constant.BASE_URL}${user.avatar}",
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(user.userName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Email: ${user.email}", fontSize = 14.sp)
                Text("Liên hệ: ${user.contact}", fontSize = 14.sp)
                Text("Tuổi: ${user.age}", fontSize = 14.sp)
                Text("Tạo lúc: ${user.dateCreated}", fontSize = 12.sp, color = Color_986601)
            }
        }
    }
}
