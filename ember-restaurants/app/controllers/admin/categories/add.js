import Ember from 'ember';

export default Ember.Controller.extend({

  categoryService: Ember.inject.service(),
  
  actions: {
    addCategory: function(){
      this.get('categoryService').addCategory(this.get('nameValue')).then(function(data){
        alert('Category successfully added');
        this.transitionToRoute('/admin/categories');
      }.bind(this),function(data){
        alert('Error adding category');
      });
    }
  }
});
