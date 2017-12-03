import Ember from 'ember';
import Filter from 'ember-restaurants/models/filter';

export default Ember.Controller.extend({
  userService: Ember.inject.service(),

  dynamicCurrent: 1,
  filter: Filter.create({
    pageNumber: 1,
    itemsPerPage: 14,
    searchText:""
  }),

  actions: {
    pageChanged(current, previous) {
      this.set('filter.pageNumber',current);
      this.get('userService').getUsersByFilter(this.get('filter')).then(function(data) {
        this.set('model.searchResponse', data);
      }.bind(this));

      this.set('dynamicCurrent', current);
    },
  addUser: function() {
    this.transitionToRoute("/admin/users/add");
  },

  deleteUser: function(user) {
    this.get('userService').deleteUser(user.id).then(function() {
      this.get('model.searchResponse.users').removeObject(user);
    }.bind(this), function(data) {
              alert(data.error);
    }.bind(this));
  },

  editUser: function(id){
    this.transitionToRoute("/admin/users/edit/"+id);
  },

  promoteUser: function(id,index){
    this.get('userService').promoteUser(id).then(function(){
      alert('User has been promoted to Admin');
      this.get('target._routerMicrolib').refresh();
    }.bind(this),function(error){
      alert('Error promoting user to Admin');
    });
  },

  search: function(){
    this.set('filter.searchText', this.get('filter.searchText').trim());
    this.get('userService').getUsersByFilter(this.get('filter')).then(function(data){
       this.set('model.searchResponse.users', data.users);
       this.set('model.searchResponse.numberOfPages',data.numberOfPages);
       this.set('dynamicCurrent',1);
    }.bind(this));
  }

}


});
