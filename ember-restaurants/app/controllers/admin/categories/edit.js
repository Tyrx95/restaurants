import Ember from 'ember';

export default Ember.Controller.extend({

  categoryService: Ember.inject.service(),

  actions: {

    editCategory: function(){
      this.get('categoryService').editCategory(this.get('model.category')).then(function(data){
        alert('Category successfully edited');
        this.transitionToRoute('/admin/categories/')
      }.bind(this),function(data){
        alert('Error editing category');
      });
    },
  }
});
