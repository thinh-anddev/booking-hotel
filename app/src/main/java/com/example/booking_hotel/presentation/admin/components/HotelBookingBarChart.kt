import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.booking_hotel.data.remote.dto.HotelStat
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import androidx.compose.ui.graphics.Color
import com.patrykandpatrick.vico.compose.component.lineComponent
import com.patrykandpatrick.vico.compose.component.marker.markerComponent
import com.patrykandpatrick.vico.compose.component.shapeComponent

@Composable
fun HotelBookingBarChart(
    modifier: Modifier = Modifier,
    stats: List<HotelStat>
) {
    val chartEntries = stats.mapIndexed { index, stat ->
        FloatEntry(index.toFloat(), stat.totalOrder.toFloat())
    }

    val modelProducer = remember(chartEntries) {
        ChartEntryModelProducer(chartEntries)
    }

    // Tạo marker hiển thị thông tin khi chạm vào cột
    val marker = markerComponent(
        label = textComponent(),
        indicator = shapeComponent(Shapes.pillShape, Color.Red),
        guideline =  lineComponent()
    )

    Chart(
        chart = columnChart(),
        chartModelProducer = modelProducer,
        startAxis = rememberStartAxis(),
        bottomAxis = rememberBottomAxis(
            valueFormatter = { x, _ ->
                stats.getOrNull(x.toInt())?.hotelId.toString() ?: ""
            }
        ),
        marker = marker,
        modifier = modifier
    )
}
