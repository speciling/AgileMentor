import * as React from 'react';
import MuiAvatar from '@mui/material/Avatar';
import MuiListItemAvatar from '@mui/material/ListItemAvatar';
import MenuItem from '@mui/material/MenuItem';
import ListItemText from '@mui/material/ListItemText';
import ListItemIcon from '@mui/material/ListItemIcon';
import Select, { selectClasses } from '@mui/material/Select';
import Divider from '@mui/material/Divider';
import AddRoundedIcon from '@mui/icons-material/AddRounded';
import FolderIcon from '@mui/icons-material/Folder';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import mockProjects from '../../../mocks/mockProjects';

export default function SelectProject() {
  const [selectedProject, setSelectedProject] = React.useState('');
  const [dialogOpen, setDialogOpen] = React.useState(false);
  const [newProjectName, setNewProjectName] = React.useState('');
  const [projects, setProjects] = React.useState(mockProjects);

  const handleChange = (event) => {
    const selectedValue = event.target.value;
    if (selectedValue === 'add-project') {
      handleAddProjectClick();
    } else {
      setSelectedProject(selectedValue);
    }
  };

  const handleAddProjectClick = () => setDialogOpen(true);
  const handleDialogClose = () => {
    setDialogOpen(false);
    setNewProjectName('');
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();
    if (!newProjectName.trim()) return;
    const maxId = projects.reduce(
      (max, project) => Math.max(max, parseInt(project.id.split('-')[1], 10)),
      0
    );
    const newProject = { id: `project-${maxId + 1}`, title: newProjectName };
    setProjects([...projects, newProject]);
    setSelectedProject(newProject.id);
    handleDialogClose();
  };

  return (
    <>
      <Select
        id="project-select"
        value={selectedProject}
        onChange={handleChange}
        displayEmpty
        fullWidth
        inputProps={{ 'aria-label': 'Select project' }}
        sx={{
          maxHeight: 56,
          width: 215,
          [`& .${selectClasses.select}`]: {
            display: 'flex',
            alignItems: 'center',
            gap: '2px',
            paddingLeft: 1,
          },
        }}
      >
        <MenuItem value="" disabled>Select Project</MenuItem>
        {projects.map((proj) => (
          <MenuItem key={proj.id} value={proj.id}>
            <MuiListItemAvatar sx={{ minWidth: 0, marginRight: 2 }}>
              <MuiAvatar sx={{ width: 28, height: 28, backgroundColor: '#ffffff', color: '#62D0FF', border: '1px solid #e0e0e0' }}>
                <FolderIcon sx={{ fontSize: '1rem' }} />
              </MuiAvatar>
            </MuiListItemAvatar>
            <ListItemText primary={proj.title} />
          </MenuItem>
        ))}
        <Divider sx={{ mx: -1 }} />
        <MenuItem value="add-project">
          <ListItemIcon>
            <AddRoundedIcon sx={{ color: '#0eaaf9', fontSize: 24 }} />
          </ListItemIcon>
          <ListItemText primary="Add Project" />
        </MenuItem>
      </Select>

      <Dialog
        open={dialogOpen}
        onClose={handleDialogClose}
        PaperProps={{
          component: 'form',
          onSubmit: handleFormSubmit,
          sx: { width: 400, height: 200 },
        }}
      >
        <DialogTitle>Add New Project</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            required
            margin="dense"
            label="Project Name"
            type="text"
            fullWidth
            variant="standard"
            value={newProjectName}
            onChange={(e) => setNewProjectName(e.target.value)}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDialogClose}>Close</Button>
          <Button type="submit">Create</Button>
        </DialogActions>
      </Dialog>
    </>
  );
}
