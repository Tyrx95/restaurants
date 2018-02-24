import Ember from 'ember';
import Filter from 'ember-restaurants/models/filter';

export default Ember.Controller.extend({
  categoryService: Ember.inject.service(),

  dynamicCurrent: 1,
  filter: Filter.create({
    pageNumber: 1,
    itemsPerPage: 14,
    searchText:""
  }),

  actions: {

    pageChanged(current, previous) {
      this.set('filter.pageNumber',current);
      this.get('categoryService').getCategoriesByFilter(this.get('filter')).then(function(data) {
        this.set('model.searchResponse', data);
      }.bind(this));

      this.set('dynamicCurrent', current);
    },
  addCategory: function() {
    this.transitionToRoute("/admin/categories/add");
  },

  deleteCategory: function(category) {
    this.get('model.searchResponse.categories').removeObject(category);
    // this.get('categoryService').deleteCategory(category.id).then(function() {
    //   this.get('model.searchResponse.categories').removeObject(category);
    // }.bind(this), function(data) {
    //           alert(data.error);
    // }.bind(this));
  },

  editCategory: function(id){
    this.transitionToRoute("/admin/categories/edit/"+id);
  },
  search: function(){
    this.set('filter.searchText', this.get('filter.searchText').trim());
    this.get('categoryService').getCategoriesByFilter(this.get('filter')).then(function(data){
       this.set('model.searchResponse.categories', data.categories);
       this.set('model.searchResponse.numberOfPages',data.numberOfPages);
       this.set('dynamicCurrent',1);
    }.bind(this));
  }

}



});
