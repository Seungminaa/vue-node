<template>
    <table id="list">
        <tr>
            <td>글제목</td>
            <td><input type="text" v-model="title"></td>
        </tr>
        <tr>
            <td colspan="2">
                <textarea v-model="content"></textarea>
            </td>
        </tr>
    </table>
    <button style="float: right;" @click="boardSave">저장</button>
</template>

<script>
export default {
    data() {
        return {
            title: '',
            content: ''
        }
    },
    methods : {
        boardSave() {
            this.$emit('save-board', this.title, this.content) //부모컨포넌트의 메소드 실행 요청, 2,3번째 값은 파라미터 값으로 넘어감
        }
    }
}
</script>