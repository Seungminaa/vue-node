<template>
    <header>
        <h2>간단 게시판</h2>
        <p>뷰로 작성하는 게시판</p>
    </header>
    <p>{{ rootData }}</p>
    <p>{{ rootList }}</p>
    <p>{{ rootStr }}</p>
</template>

<script>
export default {
    props:['rootData'],
    // 데이터 받기(provide,inject / 읽기전용)
    inject:['rootList','rootStr']
}
</script>
