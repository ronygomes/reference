# Vim Reference

### Start vim with external configuration

```shell
# -u NONE: No vimrc, -N: no vi compatibility
$ vim -u NONE -N

# Or start vim with other configuration
$ vim -u minimal.vim
```

```vim
" Filename: minimal.vim
" By default vim starts in `compatible` mode if `~/.vimrc` file not found
" So using `-u` makes vim `compatible` if not specified

set nocompatible
filetype plugin on
```

### Helpful commands for Search and Substitute
* `&`  - Repeat previous substitution
* `@:` - Repeat last Ex command
* `*`  - Search word under cursor
* `#`  - Search word under cursor backward

### Miscellaneous
* `<C-a>`, `<C-x>` - Increment/Decrements digit
* `g~`, `gu`, `gU`, `gUgU`/`gUU` - Toggle Case, Lower, Upper, Upper current line
* `<C-h>`, `<C-w>`, `<C-u>` - Delete char, word, line in **insert mode**
* `<C-[>` - Same as Escape
* `<C-o>` - Execute a single command in insert mode

