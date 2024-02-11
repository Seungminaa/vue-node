<template>
    <footer>
        <h3>2강 입니다.</h3>
        <div v-html="data"></div>
        <!-- html 템플릿을 그대로 사용-->
        <slot name="header"></slot>
        <slot name="footer"></slot>
        
        <slot></slot>
        
    </footer>
</template>

<script>
export default {
    props: ['data']
}
</script>
