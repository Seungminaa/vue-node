<template>
        <ul id="myUL">
            <li v-for="tod in list" v-bind:key="tod.no" v-bind:class="{checked: tod.cancelFlag}" @click="cancleTodo(tod.no)">{{ tod.todo }}<span class="close"  @click="deleteTodo(tod.no)">X</span></li>
        </ul>
</template>

<script>

export default{ 
    props: ['list'],
    methods:{
        deleteTodo(no) { 
            this.$emit('todo-delete',no)
        },
        cancleTodo(no) {
            this.$emit('todo-cancle',no)
        }
    }
}
</script>