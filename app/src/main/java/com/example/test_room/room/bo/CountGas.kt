package com.example.test_room.room.bo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.firebase.database.annotations.NotNull


@Entity(
    tableName = "CountGas",
    indices = arrayOf(Index(value = ["dataDay"])),  // db 쿼리 속도 column  index지정
    inheritSuperIndices = true,   // true시 부모 클래스에 선언된 인덱스가 현재의 entiry클래스로 옮겨짐
    primaryKeys = arrayOf("bikename"), // 기본키 지정시킬 칼럼값
    foreignKeys = arrayOf(   // 외래키 지정
        ForeignKey(
            entity = Userdata::class,   // 외래키 연결대상 클래스
            parentColumns = arrayOf("bikename"), // 연결대상 필드명
            childColumns = arrayOf("bikename"),  //참조한 부모의 key값을 저장한 현재 값
            onDelete = ForeignKey.CASCADE//,  // 삭제될 경우 같이 삭제 설정
            //onUpdate = ForeignKey.CASCADE // 부모의 값이 UPDATE 될때 이뤄질 행위
        )
    )
    //,ignoredColumns = arrayOf("image")     /// 생성되지 않기 원하는 컬럼
)
data class CountGas(
    @PrimaryKey var uid: Long, // 유지 순서
    var beforeOdd: Int,
    var afterOdd: Int,
    @NotNull
    var bikename: String, // 바이크 이름 구분자
    @NotNull
    var dataDay: String,// 주유 날짜 및시간?
    @NotNull
    var gasLitter: Long, // 주유 리터
    @NotNull
    var wonLitter: Int, // 리터당 가격
    @NotNull
    var allwon: Int, // 주유 금액
    var tankKanBefore: Int,// 주유 전 칸수
    var tankKanAfter: Int // 주유 후 칸
) {
    constructor() : this(0, 0,0, "", "", 0, 0, 0, 0, 0)
}