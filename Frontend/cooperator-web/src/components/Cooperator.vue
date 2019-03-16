<template>
  <div id="cooperator">
    <h2>Dummy page</h2>
    <table>
      <tr>
        <td>Yo dawg</td>
        <td>Wassap?</td>
      </tr>
      <tr>
        <td>
          <input type="text" placeholder="Person Name">
        </td>
        <td>
          <button>Click Me!</button>
        </td>
      </tr>
    </table>
    <p>
      <span style="color:red">Errorz</span>
    </p>
  </div>
</template>
<script>
  export default {
    name: 'cooperator',
    data () {
      return {
        people: [],
        newPerson: '',
        errorPerson: '',
        response: []
      }
    },
  }
</script>
<style>
  #cooperator {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
</style>
