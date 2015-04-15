module.exports = function(grunt) {
// Project configuration.
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		clean: {
			build: {
				src: ['webapps/css/jado_con.css', 'webapps/css/jado.css',
				'webapps/js/jado_encrypt_con.js', 'webapps/js/jado_encrypt.js',
				'webapps/js/jado_con.js', 'webapps/js/jado.js']
			}
		},
		//concat 설정
		concat:{
			basic_and_extras: {
				files: {
					'webapps/js/jado_encrypt_con.js': ['webapps/js/encryptJS/*.js'],
					'webapps/js/jado_con.js': ['webapps/js/jadoJS/*.js'],
					'webapps/css/jado_con.css': ['webapps/css/lib/reset.css', 'webapps/css/lib/grid.css', 'webapps/css/jadoCSS/*.css']
					//concat 결과 파일: concat 타겟 설정(앞에서부터 순서대로 합쳐진다.)
				}
			}
		},
		//uglify 설정
		uglify: {
			options: {
			},
			my_target: {
				files: {
					'webapps/js/jado_encrypt.js': ['webapps/js/jado_encrypt_con.js'],
					'webapps/js/jado.js': ['webapps/js/jado_con.js']
				}
			}
		},
		//css uglify 설정
		cssmin: {
   			dist: {
   			    src: 'webapps/css/jado_con.css',
      			dest: 'webapps/css/jado.css'
		    }
		}
	});

// Load the plugin that provides the "uglify", "concat" tasks.
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-yui-compressor');
	grunt.loadNpmTasks('grunt-contrib-uglify');
// Default task(s).
	grunt.registerTask('default', ['clean', 'concat', 'uglify', 'cssmin']); //grunt 명령어로 실행할 작업
};
