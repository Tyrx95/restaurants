import Ember from 'ember';

export default Ember.Service.extend({
  ajaxService: Ember.inject.service(),

  getCategoriesByFilter: function(filter) {
    return this.get('ajaxService').request('/admin/getFilteredCategories', {
      method: 'POST',
      data: JSON.stringify({
        searchText: filter.searchText,
        pageNumber: filter.pageNumber,
        itemsPerPage: filter.itemsPerPage
      })
    });
  },
  addCategory: function(nameValue) {
    return this.get('ajaxService').request('/admin/addCategory', {
      method: 'POST',
      data: JSON.stringify({
        name:nameValue
      })
    });
  },

  deleteCategory: function(_id) {
    return this.get('ajaxService').request('/admin/deleteCategory', {
      method: 'POST',
      data: JSON.stringify({
        id:_id
      })
    });
  },
  editCategory: function(category){
    return this.get('ajaxService').request('/admin/editCategory',{
      method: 'POST',
      data: JSON.stringify({
        id: category.id,
        name:category.name
      })
    });
  },
  getCategoryDetails: function(_id) {
    return this.get('ajaxService').request('/admin/getCategoryDetails', {
      method: 'POST',
      data: JSON.stringify({
        id:_id
      })
    });
  }



});
