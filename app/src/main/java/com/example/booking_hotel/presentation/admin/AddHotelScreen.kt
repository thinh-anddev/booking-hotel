package com.example.booking_hotel.presentation.admin

import android.annotation.SuppressLint
import android.graphics.Color
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.booking_hotel.data.remote.dto.HotelDTO
import com.example.booking_hotel.data.remote.dto.ImageDTO
import com.example.booking_hotel.ui.theme.Color_4B5842
import com.example.booking_hotel.ui.theme.Color_986601

@SuppressLint("MutableCollectionMutableState")
@Composable
fun AddHotelScreen(
    navController: NavController,
    viewModel: AdminViewModel= hiltViewModel()
) {
    val context= LocalContext.current
    val hotelTypes = listOf("Resort", "Hotel", "Hostel", "Apartment")
    val hotelClasses = listOf("1-star", "2-star", "3-star", "4-star", "5-star")

    var type by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var link by remember { mutableStateOf("") }
    var checkIn by remember { mutableStateOf("") }
    var checkOut by remember { mutableStateOf("") }
    var hotelClass by remember { mutableStateOf("") }
    var remainRooms by remember { mutableStateOf("") }

    var images by remember { mutableStateOf(mutableListOf<Pair<String, String>>()) }

    val scrollState = rememberScrollState()

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val imageUriStr = it.toString()
            images.add(Pair(imageUriStr, imageUriStr))
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Thêm khách sạn") },
                navigationIcon = {
                    IconButton(onClick ={navController.popBackStack()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                var expanded1 by remember { mutableStateOf(false) }
                var expanded2 by remember { mutableStateOf(false) }

                Column {
                    OutlinedTextField(
                        value = type,
                        onValueChange = { },
                        label = { Text("Loại khách sạn") },
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth().clickable { expanded1 = true },
                        trailingIcon = {
                            Icon(
                                imageVector = if (expanded1) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                                contentDescription = null
                            )
                        }
                    )
                    DropdownMenu(expanded = expanded1, onDismissRequest = { expanded1 = false }) {
                        hotelTypes.forEach { option ->
                            DropdownMenuItem(onClick = {
                                type = option
                                expanded1 = false
                            }) {
                                Text(option)
                            }
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label ={Text("Tên khách sạn")},
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = link,
                    onValueChange = { link = it },
                    label = {Text("Link khách sạn")},
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri)
                )

                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = checkIn,
                    onValueChange = { checkIn = it },
                    label = { Text("Giờ nhận phòng (check-in)") },
                    placeholder = { Text("VD: 14:00") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = checkOut,
                    onValueChange = { checkOut = it },
                    label = { Text("Giờ trả phòng (check-out)") },
                    placeholder = { Text("VD: 11:00") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(Modifier.height(8.dp))
                Column {
                    OutlinedTextField(
                        value = hotelClass,
                        onValueChange = { },
                        label = { Text("Hạng khách sạn") },
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth().clickable { expanded2 = true },
                        trailingIcon = {
                            Icon(
                                imageVector = if (expanded2) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                                contentDescription = null
                            )
                        }
                    )
                    DropdownMenu(expanded = expanded2, onDismissRequest = { expanded2 = false }) {
                        hotelClasses.forEach { option ->
                            DropdownMenuItem(onClick = {
                                hotelClass = option
                                expanded2 = false
                            }) {
                                Text(option)
                            }
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = remainRooms,
                    onValueChange = { remainRooms = it.filter { c -> c.isDigit() } },
                    label = { Text("Số phòng còn lại") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(Modifier.height(16.dp))
                Text("Ảnh khách sạn", fontWeight = FontWeight.SemiBold)

                Spacer(Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    images.forEach { (thumb, _) ->
                        Image(
                            painter = rememberAsyncImagePainter(model = thumb),
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .border(1.dp, Color_986601, RoundedCornerShape(8.dp))
                        )
                    }

                    IconButton(onClick = {
                        imagePickerLauncher.launch("image/*")
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Chọn ảnh")
                    }
                }

                Spacer(Modifier.height(24.dp))

                Spacer(Modifier.height(24.dp))
                Button(
                    onClick = {
                        // Chuẩn bị DTO gửi đi
                        val dto = HotelDTO(
                            type = type,
                            name = name,
                            link = link,
                            checkInTime = checkIn,
                            checkOutTime = checkOut,
                            hotelClass = hotelClass,
                            extractedHotelClass = hotelClass.filter { it.isDigit() }.toIntOrNull(),
                            overallRating = null,
                            reviews = null,
                            locationRating = null,
                            propertyToken = "", // bạn tự thêm nếu cần
                            serpapiPropertyDetailsLink = null,
                            remainRooms = remainRooms.toIntOrNull(),
                            amenities = null, // có thể thêm input nếu cần
                            images = images.map { (thumb, original) -> ImageDTO(thumb, original) }
                        )
                        viewModel.addHotel(dto,context)
                        Toast.makeText(context,"Luu thanh cong", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = name.isNotBlank() && type.isNotBlank()
                ) {
                    Text("Lưu khách sạn")
                }
            }
        }

}

@Composable
fun DropdownInput(
    label: String,
    options: List<String>,
    selected: String,
    onSelect: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = selected,
            onValueChange = { },
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier.fillMaxWidth().clickable { expanded = true },
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            }
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onSelect(option)
                    expanded = false
                }) {
                    Text(option)
                }
            }
        }
    }
}

@Composable
fun ImageInputRow(
    index: Int,
    thumbnail: String,
    originalImage: String,
    onThumbnailChange: (String) -> Unit,
    onOriginalChange: (String) -> Unit,
    onRemove: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color_4B5842, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text("Ảnh #${index + 1}", fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
            value = thumbnail,
            onValueChange = onThumbnailChange,
            label = { Text("Thumbnail URL") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri)
        )
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
            value = originalImage,
            onValueChange = onOriginalChange,
            label = { Text("Original Image URL") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri)
        )
        Spacer(Modifier.height(4.dp))
        TextButton(
            onClick = onRemove,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Xóa ảnh", color = Color_4B5842)
        }
    }
}}